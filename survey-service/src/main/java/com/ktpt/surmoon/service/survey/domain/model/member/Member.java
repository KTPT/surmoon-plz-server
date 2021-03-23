package com.ktpt.surmoon.service.survey.domain.model.member;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;

    public Member(String name, String email) {
        this.id = null;
        this.name = name;
        this.email = email;
    }

    public void updateName(String name) {
        this.name = name;
    }

    public boolean isNameChanged(String name) {
        return !this.name.equals(name);
    }
}
