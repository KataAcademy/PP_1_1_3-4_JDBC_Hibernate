package jm.task.core.jdbc.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Users", schema = "default")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastName;
    private Byte age;
}

//                    id SERIAL PRIMARY KEY,
//                    name varchar(255) NOT NULL,
//                    lastname varchar(255) NOT NULL,
//                    age smallint(99) NOT NULL);
