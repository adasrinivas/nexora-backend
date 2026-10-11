package com.nexora.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "certifications")
public class Certification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String issuer;

    private String issueDate;

    private String credentialUrl;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Certification() {
    }

    public Certification(
            String name,
            String issuer,
            String issueDate,
            String credentialUrl,
            User user) {

        this.name = name;
        this.issuer = issuer;
        this.issueDate = issueDate;
        this.credentialUrl = credentialUrl;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public String getCredentialUrl() {
        return credentialUrl;
    }

    public void setCredentialUrl(String credentialUrl) {
        this.credentialUrl = credentialUrl;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
