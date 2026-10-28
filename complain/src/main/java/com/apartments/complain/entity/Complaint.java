package com.apartments.complain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;


@Entity
@Table(name = "complaints")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "ticket_number", nullable = false, unique = true)
    private String ticketNumber;

    // M:1 Relationships
    @Column(name = "resident_id", nullable = false)
    private int residentId; // we will fetch it from jwt token

    @Column(name = "assigned_staff_id")
    private int assignedStaffId;

    // Issue Info flat number
    @Column(nullable = false)
    private String unitNumber;

    @Column(nullable = false, length = 150)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;


    @Column(nullable = false)
    private String  category;


    @Column(name = "location_type", nullable = false)
    private String locationType;



    private String status ; // by default submitted


    private String priority ; // by default medium

    // Audit and logs
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "expected_resolution_at")
    private LocalDateTime expectedResolutionAt;

    @Column(name = "resolved_at")
    private LocalDateTime resolvedAt;

    @Column(name = "closed_at")
    private LocalDateTime closedAt;

}