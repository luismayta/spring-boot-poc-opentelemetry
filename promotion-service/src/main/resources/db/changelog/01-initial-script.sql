CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS "tests" (
  "id" int NOT NULL, 
  CONSTRAINT "pk_tests" PRIMARY KEY ("id")
);
