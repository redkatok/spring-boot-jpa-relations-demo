package io.katkov.spring_boot_jpa_relations_demo.entity._5_les_many_to_one_unidirectional.cascaded;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "jpa_relations", name = "item")
@ToString(exclude = "company")
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor
@Setter
@Getter
public class ItemWithCascadeAllToBox {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;

//    optional регулирует вид джоина - false=inner true=left
    @ManyToOne(optional = false,fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="box_id")
    private BoxUnderCascadeAll box;


}
