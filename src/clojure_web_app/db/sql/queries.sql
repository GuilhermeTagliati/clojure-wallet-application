-- A :result value of :n below will return affected rows:
-- :name sql-select-users :? :*
-- :doc Get All Users
select * from "public"."user";

-- A ":result" value of ":1" specifies a single record
-- (as a hashmap) will be returned
-- :name sql-select-user-by-id :? :1
-- :doc Get User By Id
select * from "public"."user" where id = :id;



-- A :result value of :n below will return affected rows:
-- :name sql-insert-user :! :n
-- :doc Save user on db
insert into "public"."user" (name)
values (:name)
