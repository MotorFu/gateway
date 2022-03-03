drop procedure if exists addColumn $$
CREATE PROCEDURE addColumn(in tn varchar(64), in cn varchar(64), in content varchar(128))
BEGIN
  declare tmpSqlStr varchar(500);
  declare tmpSqlToRun varchar(500);
  DECLARE _count INT;

  SET _count = (SELECT COUNT(*)
                FROM INFORMATION_SCHEMA.COLUMNS
                WHERE TABLE_NAME = tn
                  AND COLUMN_NAME = cn);
  IF _count = 0
  THEN
    set tmpSqlStr = ' ';
    set tmpSqlStr = concat(tmpSqlStr, '  ALTER TABLE ', tn);
    set tmpSqlStr = concat(tmpSqlStr, '  ADD COLUMN ', concat('`', cn, '`'));
    set tmpSqlStr = concat(tmpSqlStr, ' ', content);
  ELSE
    set tmpSqlStr = ' ';
    set tmpSqlStr = concat(tmpSqlStr, '  ALTER TABLE ', tn);
    set tmpSqlStr = concat(tmpSqlStr, '  MODIFY COLUMN ', concat('`', cn, '`'));
    set tmpSqlStr = concat(tmpSqlStr, ' ', content);
  END IF;
  set @sql = tmpSqlStr;
  prepare tmpSqlToRun from @sql;
  EXECUTE tmpSqlToRun;

END $$


