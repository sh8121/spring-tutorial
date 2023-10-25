package com.sh8121.springcore.tutorial.n2_psa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Id;
import jakarta.persistence.Persistence;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.orm.jpa.EntityManagerHolder;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.transaction.support.TransactionSynchronizationManager;

public class N4_JpaTransactionManager {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
        PlatformTransactionManager transactionManager = new JpaTransactionManager(emf);
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionAttribute());
        try {
            EntityManagerHolder emh = (EntityManagerHolder) TransactionSynchronizationManager.getResource(emf);
            EntityManager em = emh.getEntityManager();
            var member = new Member();
            member.setMemberId("developer");
            member.setMoney(5000);
            em.persist(member);
            transactionManager.commit(status);
        } catch (Exception ex) {
            transactionManager.rollback(status);
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
