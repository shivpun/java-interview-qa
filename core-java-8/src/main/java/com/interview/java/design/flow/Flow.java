package com.interview.java.design.flow;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public final class Flow {

	private final Function<List<Information>, Optional<Information>> value;

	private final List<Information> args;
	
	private final Optional<InformationResult> result;

	private Flow exist;

	private Flow next;
	
	private Flow start;
	
	private boolean closed = false;
	
	private Flow(Function<List<Information>, Optional<Information>> value, List<Information> args, InformationResult result) {
		this.start = this;
		this.value = value;
		this.args = args;
		this.result = Optional.ofNullable(result);
	}
	
	private Flow(Function<List<Information>, Optional<Information>> value, List<Information> args, InformationResult result, Flow start) {
		this.value = value;
		this.args = args;
		this.result = Optional.ofNullable(result);
		this.start = start;
	}
	
	private static Flow of(Function<List<Information>, Optional<Information>> value, List<Information> args, InformationResult result, Flow start) {
		return new Flow(value, args, result, start);
	}

	public static Flow of(Function<List<Information>, Optional<Information>> value, List<Information> args, InformationResult result) {
		return new Flow(value, args, result);
	}

	public Flow ifPresent(Flow flow) {
		this.exist = flow;
		return Optional.ofNullable(this.exist).orElse(this);
	}

	public Flow ifPresent(Function<List<Information>, Optional<Information>> value, List<Information> args, InformationResult result) {
		return ifPresent(Flow.of(value, args, result, this.start));
	}

	public Flow orNext(Flow flow) {
		this.next = flow;
		return Optional.ofNullable(this.next).orElse(this);
	}

	public Flow orNext(Function<List<Information>, Optional<Information>> value, List<Information> args, InformationResult result) {
		return orNext(Flow.of(value, args, result, this.start));
	}
	
	public Flow end() {
		this.start.closed = true;
		return this.start;
	}

	public Optional<InformationResult> process() throws IllegalAccessException {
		if(!this.start.closed) {
			throw new IllegalAccessException("Flow is not yet closed");
		}
		Optional<Flow> present = Optional.ofNullable(this.exist);
		Optional<Flow> orNext = Optional.ofNullable(this.next);
		Optional<InformationResult> infoResultOpt = this.result;
		Optional<Information> infOpt = value.apply(args);
		if (infOpt.isPresent() && present.isPresent()) {
			infoResultOpt = Optional.ofNullable(this.exist.process().orElse(null));
		}
		if (!this.result.isPresent() || infOpt.isPresent()) {
			return infoResultOpt;
		}
		Flow nextFlow = orNext.orElse(Flow.of(informations->Optional.empty(), null, null, this.start));
		return nextFlow.process();
	}
}
