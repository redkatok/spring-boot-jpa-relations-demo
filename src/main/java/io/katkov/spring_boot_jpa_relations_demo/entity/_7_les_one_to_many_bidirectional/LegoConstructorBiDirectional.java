package io.katkov.spring_boot_jpa_relations_demo.entity._7_les_one_to_many_bidirectional;

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
public class LegoConstructorBiDirectional {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    @OneToMany(mappedBy = "legoConstructorBiDirectional", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LegoBlockBidirectional> legoBlocks;

    public void addLegoBlocks(List<LegoBlockBidirectional> legoBlockBidirectionalList) {
        legoBlockBidirectionalList.forEach(lb -> lb.setLegoConstructorBiDirectional(this));
        this.setLegoBlocks(legoBlockBidirectionalList);
    }

    public void addLegoBlock(LegoBlockBidirectional legoBlockBidirectional) {
        legoBlockBidirectional.setLegoConstructorBiDirectional(this);
        this.legoBlocks.add(legoBlockBidirectional);
    }

    public void removeLegoBlock(LegoBlockBidirectional legoBlockBidirectional) {
        legoBlocks.remove(legoBlockBidirectional);
        legoBlockBidirectional.setLegoConstructorBiDirectional(null);
    }
}
