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

-- :name sql-delete-user-by-id :! :n
-- :doc deletes user
delete from "public"."user" where id = :id

-- :name sql-update-user :! :n
-- :doc updates a user
update "public"."user"
set name = :name
where id = :id


-- WALLETS

-- A :result value of :n below will return affected rows:
-- :name sql-select-wallet-by-user-id :? :*
-- :doc Get all the wallets of one user
SELECT u.name AS user,  w.name AS wallet, w.id::TEXT FROM "public"."wallet" w INNER JOIN "public"."user" u ON u.id = w.id_user WHERE u.id = :id;

-- A :result value of :n below will return affected rows:
-- :name sql-select-wallet-by-id :? :*
-- :doc Get wallet by id
SELECT w.name, o.quantity, o.type, o.timestamp::TEXT FROM "public"."wallet" w INNER JOIN "public"."operation" o ON w.id = o.id_wallet WHERE w.id = :id;


-- A :result value of :n below will return affected rows:
-- :name sql-insert-wallet :! :n
-- :doc Save Wallet on db
insert into "public"."wallet" (id, id_user, name)
values (:id, :idUser, :name)

-- :name sql-update-wallet :! :n
-- :doc updates a wallet
update "public"."wallet"
set name = :name
where id = :id

-- :name sql-delete-wallet-by-id :! :n
-- :doc deletes wallet
delete from "public"."wallet" where id = :id


-- OPERATIONS

-- A :result value of :n below will return affected rows:
-- :name sql-insert-operation :! :n
-- :doc Save Operation on db
insert into "public"."operation" (id_wallet, quantity, type, timestamp) 
values (:idWallet, :quantity, :type, now());
