package kr.hs.gbsw.tree.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

//@Entity
//@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OldUser {
    @Id
    private String id;
    @JsonIgnore
    private String password;
    private String name;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
