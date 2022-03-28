package com.nms.uoc.model.entity;

import com.nms.uoc.contain.DELETED;
import com.nms.uoc.contain.STATUS;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "USER_ENTITY")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "UUID")
    private String uuid;

    @Column(name = "COMPANY_ID")
    protected Long companyId;

    @Column(name = "CREATE_USER_ID")
    protected Long createUserId;

    @Column(name = "MODIFIED_USER_ID")
    protected Long modifiedUserId;

    @Column(name = "MODIFIED_DATE")
    protected Date modifiedDate;

    @Column(name = "CREATE_DATE")
    private Date createDate;

    @Column(name = "USERNAME",nullable = false, unique = true)
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_ACTIVE")
    private String lastName;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "VERSION")
    private Integer version;

    @Column(name = "STATUS")
    private STATUS status;

    @Column(name = "DELETED")
    public DELETED deleted;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToOne
    @JoinColumn(name="role_id")
    private RoleTable role;

    public void check() {
        if (getCompanyId() == null || getCompanyId() == 0) {
            setCompanyId(1008L);
        }
        if (getCreateUserId() == null || getCreateUserId() == 0) {
            setCreateUserId(0L);
        }
        if (getModifiedUserId() == null || getModifiedUserId() == 0) {
            setModifiedUserId(0L);
        }
        if (getModifiedDate() == null) {
            setModifiedDate(new Date());
        }
        if (getCreateDate() == null) {
            setCreateDate(new Date());
        }
        if (getCreateDate() != null) {
            setModifiedDate(new Date());
        }
        if (getVersion() == null || getVersion() == 0) {
            setVersion(1);
        }
        if (getUuid() == null) {
            setUuid(UUID.randomUUID().toString());
        }
        if (getDeleted() == null) {
            setDeleted(DELETED.FALSE);
        }
        if (getStatus()==null){
            setStatus(STATUS.ACTIVE);
        }
    }
}
