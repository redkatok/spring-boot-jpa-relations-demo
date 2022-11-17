package io.katkov.spring_boot_jpa_relations_demo.entity._6_les_one_to_many_unidirectional.cascaded;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(schema = "jpa_relations", name = "lego_constructor")
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor
@Setter
@Getter
public class LegoConstructorCascaded {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "lego_constructor_id")
    private List<LegoBlockUnderCascade> legoBlocks;

}
