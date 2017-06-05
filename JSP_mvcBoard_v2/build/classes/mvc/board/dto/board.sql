SELECT * FROM DUAL;

DROP TABLE mvc_board;

CREATE TABLE mvc_board (
	num			NUMBER(5) 						,	--�۹�ȣ
	writer		VARCHAR2(20)	NOT NULL		,	--�ۼ���
	passwd		VARCHAR2(10)	NOT NULL		,	-- ���
	subject		VARCHAR2(50)	NOT NULL		,	--������
	content		VARCHAR2(500)	NOT NULL		,	--�۳���
	readCnt		NUMBER(5)		DEFAULT 0		,	--��ȸ��
	ref			NUMBER(8)		DEFAULT 0		,	--�׷�ȭ ���̵�
	ref_step	NUMBER(5)		DEFAULT 0		,	--�׷콺��
	ref_level	NUMBER(5)		DEFAULT 0		,	--�׷췹��
	reg_date	TIMESTAMP		DEFAULT SYSDATE	,	--�ۼ���
	ip			VARCHAR2(15)					,
	
	CONSTRAINT mvc_board_num_pk PRIMARY KEY(num)
);

---------------------------------------------------------------------------------------

-- ������ ������

 DROP SEQUENCE board_seq;
 
 CREATE SEQUENCE board_seq
 	START WITH 1 INCREMENT BY 1 MAXVALUE 99999;

---------------------------------------------------------------------------------------

-- ���� ���̺� ��ȸ

 SELECT * FROM tabs;

 TABLE_NAME TABLESPACE_NAME CLUSTER_NAME IOT_NAME STATUS PCT_FREE PCT_USED INI_TRANS MAX_TRANS INITIAL_EXTENT NEXT_EXTENT MIN_EXTENTS MAX_EXTENTS PCT_INCREASE FREELISTS FREELIST_GROUPS LOGGING BACKED_UP NUM_ROWS BLOCKS EMPTY_BLOCKS AVG_SPACE CHAIN_CNT AVG_ROW_LEN AVG_SPACE_FREELIST_BLOCKS NUM_FREELIST_BLOCKS DEGREE     INSTANCES  CACHE TABLE_LOCK SAMPLE_SIZE LAST_ANALYZED         PARTITIONED IOT_TYPE TEMPORARY SECONDARY NESTED BUFFER_POOL FLASH_CACHE CELL_FLASH_CACHE ROW_MOVEMENT GLOBAL_STATS USER_STATS DURATION SKIP_CORRUPT MONITORING CLUSTER_OWNER DEPENDENCIES COMPRESSION COMPRESS_FOR DROPPED READ_ONLY SEGMENT_CREATED RESULT_CACHE
 ---------- --------------- ------------ -------- ------ -------- -------- --------- --------- -------------- ----------- ----------- ----------- ------------ --------- --------------- ------- --------- -------- ------ ------------ --------- --------- ----------- ------------------------- ------------------- ---------- ---------- ----- ---------- ----------- --------------------- ----------- -------- --------- --------- ------ ----------- ----------- ---------------- ------------ ------------ ---------- -------- ------------ ---------- ------------- ------------ ----------- ------------ ------- --------- --------------- ------------
 MVC_BOARD  USERS           NULL         NULL     VALID        10     NULL         1       255          65536     1048576           1  2147483645         NULL      NULL            NULL YES     N             NULL   NULL         NULL      NULL      NULL        NULL                      NULL                NULL          1          1     N ENABLED           NULL NULL                  NO          NULL     N         N         NO     DEFAULT     DEFAULT     DEFAULT          DISABLED     NO           NO         NULL     DISABLED     YES        NULL          DISABLED     DISABLED    NULL         NO      NO        YES             DEFAULT
 MVC_MEMBER USERS           NULL         NULL     VALID        10     NULL         1       255          65536     1048576           1  2147483645         NULL      NULL            NULL YES     N                5      5            0         0         0          60                         0                   0          1          1     N ENABLED              5 2017-05-26 12:00:03.0 NO          NULL     N         N         NO     DEFAULT     DEFAULT     DEFAULT          DISABLED     YES          NO         NULL     DISABLED     YES        NULL          DISABLED     DISABLED    NULL         NO      NO        YES             DEFAULT
 ;

 
 select * from mvc_board;
 
  NUM WRITER PASSWD SUBJECT CONTENT READCNT REF REF_STEP REF_LEVEL REG_DATE IP
 --- ------ ------ ------- ------- ------- --- -------- --------- -------- --
;
 
delete  MVC_BOARD where num=1;
select * from mvc_board order by num;
  
 INSERT INTO MVC_BOARD (num, writer, passwd, subject, content, readcnt, ref, ref_step, ref_level, reg_date, ip)
 	VALUES (board_swq.nextval, '�ۼ���', '���', '������', '�۳���', 0, board_swq.currval, 0, 0, sysdate, '192.168.1.1' );
 	
 INSERT INTO MVC_BOARD (num, writer, passwd, subject, content, readcnt, ref, ref_step, ref_level, reg_date, ip)
 VALUES(board_swq.nextval,'jec1','1','������1','�۳���1',0,board_swq.currval,0,0,sysdate,'192.168.1.1');

 
--- ------ ------ ------- ------- ------- --- -------- --------- -------- --

SELECT * 
FROM ( SELECT  num, writer, passwd, subject, content, readcnt, ref, ref_step, ref_level, reg_date, ip, rownum rNum
	   FROM ( SELECT * FROM mvc_board 
	   		  ORDER BY ref DESC, ref_step ASC ) 
	  ) 
WHERE rNum >=1 AND rNum <=10;



INSERT INTO MVC_BOARD (num, writer, passwd, subject, content, readcnt, ref, ref_step, ref_level, reg_date, ip)
'jec10','10','������10','�۳���10',9,board_swq.currval,0,9,sysdate,'192.168.1.10');VALUES(board_swq.nextval,'jec10','10','������10','�۳���10',9,board_swq.currval,0,9,sysdate,'192.168.1.10');

--- ------ ------ ------- ------- ------- --- -------- --------- -------- --

update MVC_BOARD set ip='192.168.1.3' where num=73;


SELECT * FROM mvc_board WHERE num=30;

UPDATE mvc_board SET passwd='1234', subject='�����Ѱ�', content='������ �� �糪?' WHERE num=70;
 
SELECT num, board_swq.currval from MVC_BOARD ;

--- ------ ------ ------- ------- ------- --- -------- --------- -------- --


select * from mvc_board order by num DESC;


