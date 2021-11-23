-- USER

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

-- WALLETS

-- A :result value of :n below will return affected rows:
-- :name sql-select-wallet-by-user-id :? :*
-- :doc Get all the wallets of one user
SELECT u.name AS user, w.name AS wallet  FROM "public"."wallet" w INNER JOIN "public"."user" u ON u.id = w.id_user WHERE u.id = :id;

-- A :result value of :n below will return affected rows:
-- :name sql-insert-wallet :! :n
-- :doc Save Wallet on db
insert into "public"."wallet" (id, id_user, name)
values (:id, :idUser, :name)