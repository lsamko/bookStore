DROP TABLE IF EXISTS book, "user", book_user;

CREATE TABLE book
(
    id   BIGINT       NOT NULL,
    name VARCHAR(255) NOT NULL,

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