package com.example.discount;

import com.example.member.Member;

public interface DiscountPolicy {

    int discount(Member member, int price);
}
