package com.date.date.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "request", schema = "date")
public class Request {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "requester")
    private int requester;
    @Basic
    @Column(name = "requested")
    private int requested;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request that = (Request) o;
        return id == that.id && requester == that.requester && requested == that.requested;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, requester, requested);
    }
    public Request(int requestedId,int requesterId){
        this.requester= requesterId;
        this.requested=requestedId;
    }
}
