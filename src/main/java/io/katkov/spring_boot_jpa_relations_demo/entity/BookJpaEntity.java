package io.katkov.spring_boot_jpa_relations_demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "book")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(exclude = "authors")
public class BookJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "book_uuid")
    private UUID book_uuid;

    @Column(unique = true, name = "book_name")
    private String bookName;

    @Column(name = "book_description")
    private String bookDescription;

    @Column(name = "bookshop_uuid")
    private UUID bookshopId;

    @OneToMany(orphanRemoval = true, mappedBy = "book")
    private List<AuthorJpaEntity> authors;

    public void addAuthor(AuthorJpaEntity author) {
        authors.add(author);
        author.setBook(this);
    }

    public void removeAuthor(AuthorJpaEntity author) {
        authors.remove(author);
        author.setBook(null);
    }


}
