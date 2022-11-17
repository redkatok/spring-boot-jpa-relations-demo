package io.katkov.spring_boot_jpa_relations_demo.entity._7_les_one_to_many_bidirectional;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "jpa_relations", name = "lego_block")
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor
@Setter
@Getter
public class LegoBlockBidirectional {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "lego_constructor_id")
    private LegoConstructorBiDirectional legoConstructorBiDirectional;


}
