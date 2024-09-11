package com.grepp.nbe1_1_clone_mw1.member.repository;

import com.grepp.nbe1_1_clone_mw1.member.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, byte[]> {
}
