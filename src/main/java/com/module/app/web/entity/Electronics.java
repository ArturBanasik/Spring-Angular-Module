package com.module.app.web.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import static com.module.app.web.entity.ElectronicsConstants.*;

/**
 * Model danych dla sprzętu
 *
 * @author Artur
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = ELECTRONICS_TABLE_NAME)
public class Electronics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ID_COLUMN_NAME)
    private long id;

    @Column(name = ELECTRONIC_TAG_COLUMN_NAME)
    private String tag;

    @Column(name = CATEGORY_COLUMN_NAME)
    private String category;

    @Column(name = BROKEN_STATUS_COLUMN_NAME)
    private boolean broken;

    @Column(name = COMMENT_COLUMN_NAME)
    private String comment;

    @Column(name = DETAILS_COLUMN_NAME)
    private String details;

}
