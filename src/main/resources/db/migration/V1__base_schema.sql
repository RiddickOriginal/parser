CREATE TABLE users
(
    id       BIGSERIAL  PRIMARY KEY,
    username VARCHAR    NOT NULL    UNIQUE,
    url      VARCHAR    NOT NULL
);
CREATE TABLE statistics
(
    id         BIGSERIAL    PRIMARY KEY,
    reputation BIGINT       NOT NULL,
    views      BIGINT       NOT NULL,
    bookmarks  BIGINT       NOT NULL,
    comments   BIGINT       NOT NULL
);
CREATE TABLE categories
(
    id   BIGSERIAL  PRIMARY KEY,
    name VARCHAR    NOT NULL    UNIQUE,
    url  VARCHAR    NOT NULL
);
CREATE TABLE articles
(
    id           BIGSERIAL  PRIMARY KEY,
    user_id      BIGINT     NOT NULL REFERENCES users (id),
    statistic_id BIGINT     NOT NULL REFERENCES statistics (id),
    publish_date TIMESTAMP  NOT NULL,
    title        VARCHAR    NOT NULL,
    description  VARCHAR    NOT NULL,
    url          VARCHAR    NOT NULL,
    UNIQUE (user_id, title)
);
CREATE TABLE articles_categories
(
    article_id    BIGINT NOT NULL REFERENCES articles (id),
    category_id   BIGINT NOT NULL REFERENCES categories (id)
);


