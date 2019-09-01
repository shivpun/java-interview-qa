package com.interview.java.design.flow;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public final class InformationFlow {

	private final Function<List<Information>, Optional<Information>> value;

	private final List<Information> args;
	
	private final Optional<InformationResult> result;

	private InformationFlow exist;

	private InformationFlow next;
	
	private InformationFlow start;
	
	private boolean closed = false;
	
	private InformationFlow(Function<List<Information>, Optional<Information>> value, List<Information> args, InformationResult result) {
		this.start = this;
		this.value = value;
		this.args = args;
		this.result = Optional.ofNullable(result);
	}
	
	private InformationFlow(Function<List<Information>, Optional<Information>> value, List<Information> args, InformationResult result, InformationFlow start) {
		this.value = value;
		this.args = args;
		this.result = Optional.ofNullable(result);
		this.start = start;
	}
	
	private static InformationFlow of(Function<List<Information>, Optional<Information>> value, List<Information> args, InformationResult result, InformationFlow start) {
		return new InformationFlow(value, args, result, start);
	}

	public static InformationFlow of(Function<List<Information>, Optional<Information>> value, List<Information> args, InformationResult result) {
		return new InformationFlow(value, args, result);
	}

	public InformationFlow ifPresent(InformationFlow informationFlow) {
		this.exist = informationFlow;
		return Optional.ofNullable(this.exist).orElse(this);
	}

	public InformationFlow ifPresent(Function<List<Information>, Optional<Information>> value, List<Information> args, InformationResult result) {
		return ifPresent(InformationFlow.of(value, args, result, this.start));
	}

	public InformationFlow orNext(InformationFlow informationFlow) {
		this.next = informationFlow;
		return Optional.ofNullable(this.next).orElse(this);
	}

	public InformationFlow orNext(Function<List<Information>, Optional<Information>> value, List<Information> args, InformationResult result) {
		return orNext(InformationFlow.of(value, args, result, this.start));
	}
	
	public InformationFlow end() {
		this.start.closed = true;
		return this.start;
	}

	public Optional<InformationResult> process() throws IllegalAccessException {
		if(!this.start.closed) {
			throw new IllegalAccessException("Flow is not yet closed");
		}
		Optional<InformationFlow> present = Optional.ofNullable(this.exist);
		Optional<InformationFlow> orNext = Optional.ofNullable(this.next);
		Optional<InformationResult> infoResultOpt = this.result;
		Optional<Information> infOpt = value.apply(args);
		if (infOpt.isPresent() && present.isPresent()) {
			infoResultOpt = Optional.ofNullable(this.exist.process().orElse(null));
		}
		if (!this.result.isPresent() || infOpt.isPresent()) {
			return infoResultOpt;
		}
		InformationFlow nextFlow = orNext.orElse(InformationFlow.of(informations->Optional.empty(), null, null, this.start));
		return nextFlow.process();
	}
}
