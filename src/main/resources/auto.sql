DELIMITER ;;
CREATE PROCEDURE auto_insert(IN count INT)
BEGIN
    DECLARE
        i INT DEFAULT 1;
    WHILE (i <= count)
        DO
            INSERT INTO board_db.board (CONTENTS, EMAIL, FILE_NAME, FILE_PATH,
                                        FILE_TITLE, HITS, LIKES, POST_DATE,
                                        TITLE)
            VALUES (CONCAT(i, '번째 글입니다.'), 'test@naver.com', '', '', '', 0, 0,
                    NOW(), CONCAT('제목', i));
            SET
                i = i + 1;
        END WHILE;
END;;
DELIMITER ;

CALL auto_insert(100);
DROP PROCEDURE auto_insert;
