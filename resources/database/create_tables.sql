CREATE TABLE "public"."user" (
    "id" serial PRIMARY KEY,
    "name" VARCHAR(250) NOT NULL
);

CREATE TABLE "public"."wallet" (
    "id" uuid PRIMARY KEY,
    "id_user" INT NOT NULL,
    "name" VARCHAR(250) NOT NULL,
    FOREIGN KEY ("id_user") REFERENCES "user" ("id")
);

CREATE TABLE "public"."operation" (
    "id" serial PRIMARY KEY,
    "id_wallet" uuid NOT NULL,
    "quantity" NUMERIC NOT NULL,
    "type" VARCHAR(30),
    "timestamp" TIMESTAMP,
    FOREIGN KEY ("id_wallet") REFERENCES "wallet" ("id")
);


INSERT INTO "public"."user"("name") VALUES ('Guilherme Tagliati');

