package com.interview.java.design.flow;

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

import com.interview.java.design.flow.Information;
import com.interview.java.design.flow.InformationResult;
import com.interview.java.design.flow.InformationServiceImpl;

public class InformationServiceTest {

	@DisplayName("Calculate Information process flow")
	@ParameterizedTest(name = "{index} => information={0}, result={1}")
	@ArgumentsSource(CustomInformationArgumentProvider.class)
	void information(Information info, InformationResult result) throws IllegalAccessException {
		List<Information> infos = Arrays.asList(new Information[] { info });
		InformationServiceImpl serviceImpl = new InformationServiceImpl();
		InformationResult actual = serviceImpl.processForPartner(infos);
		assertEquals(result.getReason(), actual.getReason());
		assertEquals(result.getAction(), actual.getAction());
	}

	static class CustomInformationArgumentProvider implements ArgumentsProvider {

		@Override
		public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
			return Stream.of(
				Arguments.of(Information.of(null, null, null), InformationResult.of("ACTIVATED", null)),
				Arguments.of(Information.of("123", "P", "N"), InformationResult.of("PARTNER_DEACTIVATED", null)),
				Arguments.of(Information.of("123", "P", "Y"), InformationResult.of("PARTNER_ACTIVATED", "Can't Disclose")),
				Arguments.of(Information.of(null, "S", "Y"), InformationResult.of("SUPPLIER_ACTIVATED", null)),
				Arguments.of(Information.of(null, "S", "N"), InformationResult.of("SUPPLIER_DEACTIVATED", null))
			);
		}
	}
}
