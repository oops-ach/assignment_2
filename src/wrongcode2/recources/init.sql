DROP TABLE IF EXISTS public."librarydb" CASCADE;

CREATE TABLE IF NOT EXISTS public."librarydb" (

                                                  id SERIAL PRIMARY KEY,
                                                  name VARCHAR(50),
    author VARCHAR(50),
    year INT,
    quantity INT
    );

CREATE SEQUENCE IF NOT EXISTS "librarydb_id_seq";

ALTER TABLE public."librarydb" ALTER COLUMN id SET DEFAULT nextval('"librarydb_id_seq"');

INSERT INTO public."librarydb" (name, author, year, quantity) VALUES
                                                                  ('Baqytsyz Zhamal', 'Mirzhakyp Dulatov', 1910, 4),
                                                                  ('On qol', 'Tolen Abdik', 1942, 5),
                                                                  ('Abay zholy', 'Mukagaly Maqataev', 1942, 21)
    ON CONFLICT DO NOTHING;

SELECT setval('"librarydb_id_seq"', (SELECT MAX(id) FROM public."librarydb"));