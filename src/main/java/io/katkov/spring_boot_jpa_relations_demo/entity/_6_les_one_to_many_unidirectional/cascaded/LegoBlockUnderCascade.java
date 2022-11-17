package io.katkov.spring_boot_jpa_relations_demo.entity._6_les_one_to_many_unidirectional.cascaded;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "jpa_relations", name = "lego_block")
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor
@Setter
@Getter
public class LegoBlockUnderCascade {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;
}
