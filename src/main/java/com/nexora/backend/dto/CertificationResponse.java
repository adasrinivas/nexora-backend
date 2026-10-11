package com.nexora.backend.dto;

public class CertificationResponse {

    private Long id;
    private String name;
    private String issuer;
    private String issueDate;
    private String credentialUrl;
    private Long userId;

    public CertificationResponse() {
    }

    public CertificationResponse(
            Long id,
            String name,
            String issuer,
            String issueDate,
            String credentialUrl,
            Long userId) {

        this.id = id;
        this.name = name;
        this.issuer = issuer;
        this.issueDate = issueDate;
        this.credentialUrl = credentialUrl;
        this.userId = userId;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
