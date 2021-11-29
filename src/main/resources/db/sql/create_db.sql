DROP TABLE IF EXISTS book, "user", book_user,user_card, description, book_description, author;

CREATE TABLE book
(
    id             BIGINT       NOT NULL,
    name           VARCHAR(255) NOT NULL,
    book_id        BIGINT       NOT NULL,
    user_id        VARCHAR(255),
    description_id BIGINT       NOT NULL,

    CONSTRAINT UNIQUE_NAME
        UNIQUE (name),
    CONSTRAINT PK_ID
        PRIMARY KEY (id)
);

CREATE TABLE "user"
(
    id         BIGINT       NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name  VARCHAR(255) NOT NULL,
    user_id    VARCHAR(255) NOT NULL,

    CONSTRAINT PK_USER_ID
        PRIMARY KEY (id)
);

CREATE TABLE book_user
(
    book_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,

    CONSTRAINT PK_USER_BOOK_ID
        PRIMARY KEY (user_id, book_id),
    CONSTRAINT FK_USER_ID
        FOREIGN KEY (user_id) REFERENCES "user" (id),
    CONSTRAINT FK_BOOK_ID
        FOREIGN KEY (book_id) REFERENCES book (id)
);


CREATE TABLE user_card
(
    id      BIGINT NOT NULL,
    user_id BIGINT NOT NULL,


    CONSTRAINT PK_USER_CARD_ID
        PRIMARY KEY (id),

    CONSTRAINT FK_USER_ID
        FOREIGN KEY (user_id) REFERENCES book (id)
);

CREATE TABLE description
(
    id             BIGINT       NOT NULL,
    text           VARCHAR(255) NOT NULL,
    book_id        BIGINT       NOT NULL,

    CONSTRAINT UNIQUE_TEXT
        UNIQUE (text),
    CONSTRAINT PK_DESCRIPTION_ID
        PRIMARY KEY (id),
    CONSTRAINT FK_BOOK_ID
        FOREIGN KEY (book_id) REFERENCES book (id)
);

ALTER TABLE book ADD CONSTRAINT FK_DESCRIPTION_ID
    FOREIGN KEY (description_id) REFERENCES description (id);

CREATE TABLE book_description
(
    id      BIGINT NOT NULL,
    book_id BIGINT NOT NULL,


    CONSTRAINT PK_BOOK_DESCRIPTION_ID
        PRIMARY KEY (id),

    CONSTRAINT FK_BOOK_ID
        FOREIGN KEY (book_id) REFERENCES description (id)
);

CREATE TABLE author
(
    id          BIGINT       NOT NULL,
    author_name VARCHAR(255) NOT NULL,

    CONSTRAINT UNIQUE_AUTHOR_NAME
        UNIQUE (author_name),
    CONSTRAINT PK_AUTHOR_ID
        PRIMARY KEY (id)
);