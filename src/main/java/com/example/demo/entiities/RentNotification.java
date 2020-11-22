package com.example.demo.entiities;

import javax.persistence.*;

@Entity
@Table(name="RENT_NOTIFICATION")
public class RentNotification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    Long id;

    @Column(name="HTML")
        private String html;

    @Column(name="TYPE")
    private String type;
}
