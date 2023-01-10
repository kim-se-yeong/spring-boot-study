package com.example.discount;

import com.example.member.Grade;
import com.example.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {

    RateDiscountPolicy rateDiscountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP 는 10% 할인이 적용됩니다.")
    void discount() {
        Member member = new Member(1L, "김세영", Grade.VIP);

        int discount = rateDiscountPolicy.discount(member, 1000);

        Assertions.assertThat(discount).isEqualTo(100);
    }
}