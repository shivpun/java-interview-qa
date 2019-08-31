package com.interview.java.optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

public class InformationServiceTest {

	@DisplayName("Calculate Information process flow")
	@ParameterizedTest(name = "{index} => information={0}, result={1}")
	@ArgumentsSource(CustomInformationArgumentProvider.class)
	void information(Information info, InformationResult result) {
		List<Information> infos = Arrays.asList(new Information[] { info });
		InformationServiceImpl serviceImpl = new InformationServiceImpl();
		InformationResult actual = serviceImpl.processForPartner(infos);
		assertEquals(result.getReason(), actual.getReason());
		assertEquals(result.getAction(), actual.getAction());
	}

	static class CustomInformationArgumentProvider implements ArgumentsProvider {

		@Override
		public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
			return Stream
					.of(Arguments.of(Information.of(null, "S", "Y"), InformationResult.of("SUPPLIER_ACTIVATED", null)));
		}
	}
}
