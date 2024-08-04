package com.sh8121.springtutorial.springcore.tutorial.n2_psa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Persistence;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class N2_JPATransaction {

    public static void main(String[] args) {
        var emf = Persistence.createEntityManagerFactory("test");
        var em = emf.createEntityManager();
        var tx = em.getTransaction();
        tx.begin();
        try {
            var member = new Member();
            member.setMemberId("developer2");
            member.setMoney(50000);
            em.persist(member);
            tx.commit();
        } catch (RuntimeException ex) {
            tx.rollback();
        } finally {
            em.close();
        }
    }

    @Entity
    @Getter
    @Setter
    @NoArgsConstructor
    @Table(name = "MEMBER")
    static class Member {

        @Id
        @Column(name = "MEMBER_ID")
        private String memberId;

        @Column(name = "MONEY")
        private int money;
    }
}
