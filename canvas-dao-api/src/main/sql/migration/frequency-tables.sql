

CREATE TABLE clinbin.max_freq_2 (loc_var_id bigint NOT NULL, max_allele_freq double precision NOT NULL, gen1000_version integer NOT NULL, source text NOT NULL);
ALTER TABLE ONLY clinbin.max_freq_2 ADD CONSTRAINT max_freq_2_pkey PRIMARY KEY (loc_var_id, gen1000_version);
ALTER TABLE ONLY clinbin.max_freq_2 ADD CONSTRAINT max_freq_2_loc_var_id_fkey FOREIGN KEY (loc_var_id) REFERENCES var.loc_var(loc_var_id);

CREATE TABLE clinbin.max_freq_4 (loc_var_id bigint NOT NULL, max_allele_freq double precision NOT NULL, gen1000_version integer NOT NULL, source text NOT NULL);
ALTER TABLE ONLY clinbin.max_freq_4 ADD CONSTRAINT max_freq_4_pkey PRIMARY KEY (loc_var_id, gen1000_version);
ALTER TABLE ONLY clinbin.max_freq_4 ADD CONSTRAINT max_freq_4_loc_var_id_fkey FOREIGN KEY (loc_var_id) REFERENCES var.loc_var(loc_var_id);

