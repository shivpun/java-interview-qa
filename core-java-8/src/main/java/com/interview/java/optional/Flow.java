package com.interview.java.optional;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public final class Flow {

	private final Function<List<Information>, Optional<Information>> value;

	private final List<Information> args;

	private Flow exist;

	private Flow next;

	private Flow(Function<List<Information>, Optional<Information>> value, List<Information> args) {
		this.value = value;
		this.args = args;
	}

	public static Flow of(Function<List<Information>, Optional<Information>> value, List<Information> args) {
		return new Flow(value, args);
	}

	public Flow ifPresent(Flow flow) {
		this.exist = flow;
		return Optional.ofNullable(this.exist).orElse(this);
	}

	public Flow ifPresent(Function<List<Information>, Optional<Information>> value, List<Information> args) {
		return ifPresent(Flow.of(value, args));
	}

	public Flow next(Flow flow) {
		this.next = flow;
		return Optional.ofNullable(this.next).orElse(this);
	}

	public Flow next(Function<List<Information>, Optional<Information>> value, List<Information> args) {
		return next(Flow.of(value, args));
	}

	public Optional<Information> process() {
		Optional<Flow> present = Optional.ofNullable(this.exist);
		Optional<Information> infOpt = value.apply(args);
		if (infOpt.isPresent() && present.isPresent()) {
			infOpt.orElse(this.exist.process().get());
		}
		if (infOpt.isPresent()) {
			return infOpt;
		}
		return next.process();
	}
}
