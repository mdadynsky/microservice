insert into party (name,create_date,version) values
('Party-1', now(), '1'),
('Party-2', now(), '1'),
('Party-3', now(), '1');

insert into item (owner_id,parent_id,serial,TYPE,CHILDREN_COUNT,create_date) values
(1,null,'serial 1', 2,0,now()),
(1,null,'serial 2', 1,2,now()),
(1,2, 'serial 2.1', 2,0,now()),
(1,2, 'serial 2.1', 2,0,now());