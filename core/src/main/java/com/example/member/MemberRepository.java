package com.example.member;

public interface MemberRepository {

    void save(Member member);

    Member findById(Long memberId);
}
