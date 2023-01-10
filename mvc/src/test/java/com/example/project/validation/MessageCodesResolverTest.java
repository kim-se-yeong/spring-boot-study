package com.example.project.validation;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.validation.DefaultMessageCodesResolver;
import org.springframework.validation.MessageCodesResolver;

public class MessageCodesResolverTest {

    MessageCodesResolver messageCodesResolver = new DefaultMessageCodesResolver();

    @Test
    void messageCodesResolverObject() {
        String[] messageCodes = messageCodesResolver.resolveMessageCodes("required", "item");
        for (String messageCode : messageCodes) {
            System.out.println(messageCode);
        }

        Assertions.assertThat(messageCodes).containsExactly("required.item", "required");
    }

    @Test
    void messageCodesResolverField() {
        String[] messageCodes = messageCodesResolver.resolveMessageCodes("required", "item", "name", String.class);
        for (String messageCode : messageCodes) {
            System.out.println(messageCode);
        }
    }
}
