package com.kingalex.timetracker.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "project_time_logs",
        indexes = {
                @Index(name = "idx_ptl_user_project_start", columnList = "user_id, project_id, start_at"),
                @Index(name = "idx_ptl_attendance_record", columnList = "attendance_record_id")
        }
)
@Getter
@Setter
@ToString(exclude = {"user", "project", "attendanceRecord"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectTimeLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @ManyToOne
    @JoinColumn(name = "attendance_record_id")
    private AttendanceRecord attendanceRecord;

    @Column(name = "start_at", nullable = false)
    private LocalDateTime startAt;

    @Column(name = "end_at")
    private LocalDateTime endAt;

    @Column(name = "duration_minutes")
    private Integer durationMinutes;

    @Column(columnDefinition = "TEXT")
    private String description;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;
}