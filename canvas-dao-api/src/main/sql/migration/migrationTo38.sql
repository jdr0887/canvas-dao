
-- index drops
DROP INDEX var.asm_loc_var_loc_var_id_idx;
DROP INDEX var.asm_loc_var_asm_id_idx;
DROP INDEX dbsnp.snp_mapping_loc_var_id_idx;
DROP INDEX dbsnp.snp_mapping_loc_var_id_idx1;
DROP INDEX hgmd.hgmd_loc_var_loc_var_id_idx;

-- these are duplicates
DROP INDEX dbsnp.snp_snp_id_idx;
DROP INDEX clinbin.bin_results_final_riskx_participant_idx1;


--alter table hgmd.hgmd_loc_var owner to vardb_admin;

-- index creates
CREATE INDEX CONCURRENTLY variants_61_2_gene_id_idx ON refseq.variants_61_2 (gene_id);
CREATE INDEX CONCURRENTLY hgmd_loc_var_loc_var_id_idx ON hgmd.hgmd_loc_var (loc_var_id);
CREATE INDEX CONCURRENTLY hgmd_loc_var_v1_loc_var_id_idx ON hgmd.hgmd_loc_var_v1 (loc_var_id);

-- Dylan's tables
CREATE SEQUENCE clinbin.analysis_note_analysis_note_id_seq START WITH 1 INCREMENT BY 1;
CREATE TABLE clinbin.analysis_note (analysis_note_id integer NOT NULL DEFAULT nextval('clinbin.analysis_note_analysis_note_id_seq'::regclass), loc_var_id bigint, hgnc_gene text, phenotype_id integer, note text, user_name text, "timestamp" timestamp with time zone, PRIMARY KEY (analysis_note_id));
ALTER TABLE clinbin.analysis_note ADD FOREIGN KEY (loc_var_id) REFERENCES var.loc_var (loc_var_id) DEFERRABLE;
ALTER TABLE clinbin.analysis_note ADD FOREIGN KEY (phenotype_id) REFERENCES clinbin.phenotypex (phenotype_id) DEFERRABLE;
ALTER TABLE clinbin.analysis_note OWNER to vardb_admin;
GRANT INSERT, SELECT ON TABLE clinbin.analysis_note TO vardb_ncgenes;
GRANT INSERT, SELECT, UPDATE, DELETE ON TABLE clinbin.analysis_note TO vardb_annot;
GRANT SELECT ON TABLE clinbin.analysis_note TO vardb_select;
GRANT ALL ON TABLE clinbin.analysis_note TO vardb_admin;

CREATE SEQUENCE clinbin.confirmed_tracking_confirmed_tracking_id_seq START WITH 1 INCREMENT BY 1;
CREATE TABLE clinbin.confirmed_tracking (confirmed_tracking_id integer NOT NULL DEFAULT nextval('clinbin.confirmed_tracking_confirmed_tracking_id_seq'::regclass), bin_type integer NULL, bin_id integer NULL, loc_var_id bigint NULL, hgnc_gene text NULL, phenotype_id integer NULL, confirmed integer NULL, user_name text NULL, "timestamp" timestamp with time zone NULL, PRIMARY KEY (confirmed_tracking_id));
ALTER TABLE clinbin.confirmed_tracking ADD FOREIGN KEY (loc_var_id) REFERENCES var.loc_var (loc_var_id) DEFERRABLE;
ALTER TABLE clinbin.confirmed_tracking ADD FOREIGN KEY (phenotype_id) REFERENCES clinbin.phenotypex (phenotype_id) DEFERRABLE;
ALTER TABLE clinbin.confirmed_tracking OWNER to vardb_admin;

GRANT INSERT, SELECT ON TABLE clinbin.confirmed_tracking TO vardb_ncgenes;
GRANT INSERT, SELECT, UPDATE, DELETE ON TABLE clinbin.confirmed_tracking TO vardb_annot;
GRANT SELECT ON TABLE clinbin.confirmed_tracking TO vardb_select;
GRANT ALL ON TABLE clinbin.confirmed_tracking TO vardb_admin;

-- adding foriegn keys constraints
ALTER TABLE refseq.variants_61_2 ADD FOREIGN KEY (gene_id) REFERENCES annot.gene (gene_id) DEFERRABLE;
ALTER TABLE refseq.variants_61_2 ADD FOREIGN KEY (variant_effect) REFERENCES refseq.variant_effect (variant_effect) DEFERRABLE;
ALTER TABLE refseq.variants_80_4 ADD FOREIGN KEY (gene_id) REFERENCES annot.gene (gene_id) DEFERRABLE;
ALTER TABLE refseq.variants_80_4 ADD FOREIGN KEY (variant_effect) REFERENCES refseq.variant_effect (variant_effect) DEFERRABLE;

ALTER TABLE refseq.variants_48_2 ALTER COLUMN strand TYPE varchar(1);
ALTER TABLE refseq.variants_61_2 ALTER COLUMN strand TYPE varchar(1);
ALTER TABLE refseq.variants_80_4 ALTER COLUMN strand TYPE varchar(1);
ALTER TABLE refseq.transcr_maps ALTER COLUMN strand TYPE varchar(1);
ALTER TABLE refseq.cds ALTER COLUMN codon_start TYPE varchar(1) USING codon_start::character varying(1);
ALTER TABLE refseq.cds ALTER COLUMN codon_start TYPE integer using codon_start::integer;
ALTER TABLE clinbin.bin_results_final_diagnostic ALTER COLUMN strand TYPE varchar(1);
ALTER TABLE clinbin.bin_results_final_diagnostic ALTER COLUMN tag TYPE varchar(5) USING tag::character varying(5);
ALTER TABLE clinbin.bin_results_final_incidentalx ALTER COLUMN tag TYPE varchar(5) USING tag::character varying(5);
ALTER TABLE clinbin.haplotypex ALTER COLUMN loc_var_id TYPE bigint USING loc_var_id::bigint;
ALTER TABLE clinbin.incidental_bin_results ALTER COLUMN loc_var_id TYPE bigint USING loc_var_id::bigint;
ALTER TABLE clinbin.missing_haps ALTER COLUMN loc_var_id TYPE bigint USING loc_var_id::bigint;
ALTER TABLE clinbin.ncgenes_frequencies ALTER COLUMN loc_var_id TYPE bigint USING loc_var_id::bigint;
ALTER TABLE esp.snp_freq_population ALTER COLUMN loc_var_id TYPE bigint USING loc_var_id::bigint;
ALTER TABLE var.allele_map ALTER COLUMN loc_var_id TYPE bigint USING loc_var_id::bigint;
ALTER TABLE refseq.region_group_regions ALTER COLUMN start_type TYPE varchar(100) USING start_type::character varying(100);
ALTER TABLE refseq.region_group_regions ALTER COLUMN end_type TYPE varchar(100) USING end_type::character varying(100);

-- have to drop much of bpow views to make this work
ALTER TABLE hgmd.hgmd_loc_var ALTER COLUMN tag TYPE varchar(5) USING tag::character varying(5);

-- similarly...must delete views from hgmd
ALTER TABLE hgmd.deletion_cgquick ALTER COLUMN tag TYPE varchar(5) USING tag::character varying(5);
ALTER TABLE hgmd.hgmd_af ALTER COLUMN tag TYPE varchar(5) USING tag::character varying(5);
ALTER TABLE hgmd.insertion_cgquick ALTER COLUMN tag TYPE varchar(5) USING tag::character varying(5);
ALTER TABLE hgmd.mutation ALTER COLUMN tag TYPE varchar(5) USING tag::character varying(5);
ALTER TABLE hgmd.sub_cgquick ALTER COLUMN tag TYPE varchar(5) USING tag::character varying(5);
ALTER TABLE hgmd.hgmd_all_t ALTER COLUMN tag TYPE varchar(5) USING tag::character varying(5);
ALTER TABLE hgmd.hgmd_annot ALTER COLUMN tag TYPE varchar(5) USING tag::character varying(5);
ALTER TABLE hgmd.hgmd_annot_old ALTER COLUMN tag TYPE varchar(5) USING tag::character varying(5);
ALTER TABLE hgmd.hgmd_loc_var_test ALTER COLUMN tag TYPE varchar(5) USING tag::character varying(5);
ALTER TABLE hgmd.hgmd_loc_var_v1 ALTER COLUMN tag TYPE varchar(5) USING tag::character varying(5);
ALTER TABLE hgmd.hgmd_snp_t ALTER COLUMN tag TYPE varchar(5) USING tag::character varying(5);
ALTER TABLE hgmd.hgmd_snp_t ALTER COLUMN tag TYPE varchar(5) USING tag::character varying(5);
ALTER TABLE clinbin.diagnostic_binning_job ALTER COLUMN gender TYPE varchar(1);
ALTER TABLE clinbin.incidental_binning_job ALTER COLUMN gender TYPE varchar(1);

alter table clinbin.bin_results_final_diagnostic rename acc_num to hgmd_acc_num;
alter table clinbin.bin_results_final_diagnostic rename tag to hgmd_tag;
alter table clinbin.bin_results_final_diagnostic add column clinvar_accession varchar(20);
alter table clinbin.bin_results_final_diagnostic add column clinvar_assertion varchar(100);
alter table clinbin.bin_results_final_diagnostic rename class_id to hgmd_class_id;
alter table clinbin.bin_results_final_diagnostic alter column hgmd_class_id drop not null;
alter table clinbin.bin_results_final_diagnostic add column clinvar_class_id integer;

alter table clinbin.report rename n_analyzed_class_1 to n_analyzed_hgmd_class_1;
alter table clinbin.report rename n_analyzed_class_2 to n_analyzed_hgmd_class_2;
alter table clinbin.report rename n_analyzed_class_3 to n_analyzed_hgmd_class_3;
alter table clinbin.report rename n_analyzed_class_4 to n_analyzed_hgmd_class_4;
alter table clinbin.report rename n_analyzed_class_5 to n_analyzed_hgmd_class_5;
alter table clinbin.report rename n_analyzed_class_6 to n_analyzed_hgmd_class_6;

alter table clinbin.report add column n_analyzed_clinvar_class_1 integer;
alter table clinbin.report add column n_analyzed_clinvar_class_2 integer;
alter table clinbin.report add column n_analyzed_clinvar_class_3 integer;
alter table clinbin.report add column n_analyzed_clinvar_class_4 integer;
alter table clinbin.report add column n_analyzed_clinvar_class_5 integer;
alter table clinbin.report add column n_analyzed_clinvar_class_6 integer;

ALTER TABLE clinbin.max_freq ALTER COLUMN max_allele_freq DROP NOT NULL;
ALTER TABLE clinbin.max_freq ALTER COLUMN source DROP NOT NULL;

ALTER TABLE clinvar.version_accession_map ALTER COLUMN clinvar_version_id TYPE bigint using clinvar_version_id::bigint;
ALTER TABLE clinvar.version_accession_map ALTER COLUMN clinvar_ref_assertion_id TYPE bigint using clinvar_ref_assertion_id::bigint;

-- casts for postgres enums to work as primitives
--CREATE OR REPLACE FUNCTION hgmd_enum_to_varchar(hgmd.hgmd_enum) RETURNS varchar STRICT IMMUTABLE LANGUAGE SQL AS 'select cast($1 as varchar);';
--CREATE OR REPLACE FUNCTION refseq_strand_to_varchar(refseq.strand_type) RETURNS varchar STRICT IMMUTABLE LANGUAGE SQL AS 'select cast($1 as varchar);';
--CREATE OR REPLACE FUNCTION clinbin_varchar_to_gender(varchar) RETURNS clinbin.gender STRICT IMMUTABLE LANGUAGE SQL AS 'select cast($1 as clinbin.gender);';

CREATE OR REPLACE FUNCTION clinbin.confirmed_tracking_insert(_bin_type integer, _bin_id integer, _loc_var_id bigint, _hgnc_gene text, _phenotype_id integer, _confirmed integer, _user_name text, _timestamp timestamp with time zone) RETURNS integer
    LANGUAGE plpgsql
    AS $$
DECLARE
    _id integer;
BEGIN
    INSERT INTO clinbin.confirmed_tracking (bin_type, bin_id, loc_var_id, hgnc_gene, phenotype_id, confirmed, user_name, timestamp)
        VALUES (_bin_type, _bin_id, _loc_var_id, _hgnc_gene, _phenotype_id, _confirmed, _user_name, _timestamp)
        RETURNING confirmed_tracking_id
        INTO _id;
    RETURN _id;
END;
$$;

ALTER FUNCTION clinbin.confirmed_tracking_insert(_bin_type integer, _bin_id integer, _loc_var_id bigint, _hgnc_gene text, _phenotype_id integer, _confirmed integer, _user_name text, _timestamp timestamp with time zone) OWNER TO vardb_admin;


CREATE OR REPLACE FUNCTION clinbin.binning_job_insert(_incidental_list_version integer, _diagnostic_list_version integer, _participant text, _bin_start timestamp with time zone, _bin_stop timestamp with time zone, _gender varchar(1), _select_bin_1 boolean, _select_bin_2a boolean, _select_bin_2b boolean, _select_bin_2c boolean, _dx_id integer, _status text, _failure_message text, _select_carrier boolean) RETURNS integer
    LANGUAGE plpgsql
    AS $$
DECLARE
    _id integer;
BEGIN
    INSERT INTO clinbin.binning_job (incidental_list_version, diagnostic_list_version, participant, bin_start, bin_stop, gender,
                                     select_bin_1, select_bin_2a, select_bin_2b, select_bin_2c, dx_id, status, failure_message, select_carrier)
        VALUES (_incidental_list_version, _diagnostic_list_version, _participant, _bin_start, _bin_stop, _gender,
                _select_bin_1, _select_bin_2a, _select_bin_2b, _select_bin_2c, _dx_id, _status, _failure_message, _select_carrier)
        RETURNING binning_job_id
        INTO _id;

    RETURN _id;
END;
$$;

ALTER FUNCTION clinbin.binning_job_insert(_incidental_list_version integer, _diagnostic_list_version integer, _participant text, _bin_start timestamp with time zone, _bin_stop timestamp with time zone, _gender varchar(1), _select_bin_1 boolean, _select_bin_2a boolean, _select_bin_2b boolean, _select_bin_2c boolean, _dx_id integer, _status text, _failure_message text, _select_carrier boolean) OWNER TO vardb_admin;

CREATE OR REPLACE FUNCTION clinbin.binning_job_insert(_incidental_list_version integer, _diagnostic_list_version integer, _participant text, _bin_start timestamp with time zone, _bin_stop timestamp with time zone, _gender varchar(1), _select_bin_1 boolean, _select_bin_2a boolean, _select_bin_2b boolean, _select_bin_2c boolean, _dx_id integer, _status text, _failure_message text, _select_carrier boolean, _vcf_file text) RETURNS integer
    LANGUAGE plpgsql
    AS $$
DECLARE
    _id integer;
BEGIN
    INSERT INTO clinbin.binning_job (incidental_list_version, diagnostic_list_version, participant, bin_start, bin_stop, gender,
                                     select_bin_1, select_bin_2a, select_bin_2b, select_bin_2c, dx_id, status, failure_message, select_carrier, vcf_file)
        VALUES (_incidental_list_version, _diagnostic_list_version, _participant, _bin_start, _bin_stop, _gender,
                _select_bin_1, _select_bin_2a, _select_bin_2b, _select_bin_2c, _dx_id, _status, _failure_message, _select_carrier, _vcf_file)
        RETURNING binning_job_id
        INTO _id;

    RETURN _id;
END;
$$;

ALTER FUNCTION clinbin.binning_job_insert(_incidental_list_version integer, _diagnostic_list_version integer, _participant text, _bin_start timestamp with time zone, _bin_stop timestamp with time zone, _gender varchar(1), _select_bin_1 boolean, _select_bin_2a boolean, _select_bin_2b boolean, _select_bin_2c boolean, _dx_id integer, _status text, _failure_message text, _select_carrier boolean, _vcf_file text) OWNER TO vardb_admin;

CREATE OR REPLACE FUNCTION clinbin.diagnostic_binning_job_insert(_diagnostic_list_version integer, _participant text, _gender varchar(1), _dx_id integer, _study text) RETURNS integer
    LANGUAGE plpgsql
    AS $$
DECLARE
    _id integer;
BEGIN
    INSERT INTO clinbin.diagnostic_binning_job (diagnostic_list_version, participant,  gender, dx_id, status, failure_message, vcf_file, study)
        VALUES ( _diagnostic_list_version, _participant,  _gender, _dx_id, 'Requested', '', '', _study)
        RETURNING binning_job_id
        INTO _id;

    RETURN _id;
END;
$$;

ALTER FUNCTION clinbin.diagnostic_binning_job_insert(_diagnostic_list_version integer, _participant text, _gender varchar(1), _dx_id integer, _study text) OWNER TO vardb_admin;

CREATE OR REPLACE FUNCTION clinbin.incidental_binning_job_insert(_incidental_list_version integer, _participant text, _gender varchar(1), _incidental_bin_id integer, _study text) RETURNS integer
    LANGUAGE plpgsql
    AS $$
DECLARE
    _id integer;
BEGIN
    INSERT INTO clinbin.incidental_binning_job (incidental_list_version, participant, gender, incidental_bin_id, status, failure_message, vcf_file, study)
        VALUES ( _incidental_list_version, _participant,  _gender, _incidental_bin_id, 'Requested', '','', _study)
        RETURNING binning_job_id
        INTO _id;

    RETURN _id;
END;
$$;

ALTER FUNCTION clinbin.incidental_binning_job_insert(_incidental_list_version integer, _participant text, _gender text, _incidental_bin_id integer, _study text) OWNER TO vardb_admin;

CREATE OR REPLACE FUNCTION clinbin.analysis_note_insert(_loc_var_id bigint, _hgnc_gene text, _phenotype_id integer, _note text, _user_name text) RETURNS VOID
       LANGUAGE plpgsql
       AS $$
BEGIN
	INSERT INTO clinbin.analysis_note (analysis_note_id, loc_var_id, hgnc_gene, phenotype_id, note, user_name, "timestamp")
	       VALUES (NEXTVAL('clinbin.analysis_note_analysis_note_id_seq'),_loc_var_id, _hgnc_gene, _phenotype_id, _note, _user_name, now());
       RETURN;		    
END;
$$;


CREATE OR REPLACE FUNCTION clinbin.analysis_class_incidental_insert(_selected_class text, _select_class_descr text, _loc_var_id integer, _user_name text, _timestamp timestamp with time zone, _hgnc_gene text, _phenotype_id text, _incidental_bin_id integer) RETURNS integer
       LANGUAGE plpgsql
       AS $$
DECLARE
    _id integer;    
BEGIN
	INSERT INTO clinbin.analysis_class_incidental (selected_class, select_class_descr, loc_var_id, user_name, timestamp, hgnc_gene, phenotype_id, incidental_bin_id)
               VALUES (_selected_class, _select_class_descr, _loc_var_id, _user_name, _timestamp, _hgnc_gene, _phenotype_id, _incidental_bin_id) RETURNING analysis_class_incidental_id INTO _id;
	RETURN _id;
END;
$$;

ALTER FUNCTION clinbin.analysis_class_incidental_insert(_selected_class text, _select_class_descr text, _loc_var_id integer, _user_name text, _timestamp timestamp with time zone, _hgnc_gene text, _phenotype_id text, _incidental_bin_id integer) OWNER TO vardb_admin;


CREATE OR REPLACE FUNCTION clinbin.analysis_class_insert(_selected_class text, _select_class_descr text, _loc_var_id bigint, _user_name text, _timestamp timestamp with time zone, _hgnc_gene text, _dx_id integer) RETURNS integer
       LANGUAGE plpgsql
       AS $$
DECLARE
    _id integer;    
BEGIN
	INSERT INTO clinbin.analysis_class (selected_class, selected_class_descr, loc_var_id, user_name, timestamp, hgnc_gene, dx_id)
               VALUES (_selected_class, _select_class_descr, _loc_var_id, _user_name, _timestamp, _hgnc_gene, _phenotype_id, _dx_id) RETURNING analysis_class_id INTO _id;
	RETURN _id;
END;
$$;

ALTER FUNCTION clinbin.analysis_class_insert(_selected_class text, _select_class_descr text, _loc_var_id integer, _user_name text, _timestamp timestamp with time zone, _hgnc_gene text, _dx_id integer) OWNER TO vardb_admin;




update refseq.transcr_maps set min_contig=sub.themin, max_contig=sub.themax from (
       select tm.refseq_transcr_maps_id as themapid, tm.refseq_transcr_ver_id as theverid,
              case when (tm.strand = '+') then min(tme.contig_start) else min(tme.contig_end) end as themin,
              case when (tm.strand = '+') then max(tme.contig_end) else max(tme.contig_start) end as themax
       	      from refseq.transcr_maps tm
              join refseq.transcr t on tm.refseq_transcr_ver_id=t.ver_id
              join refseq.transcr_maps_exons tme on tme.refseq_transcr_maps_id=tm.refseq_transcr_maps_id
              group by t.ver_id, tm.refseq_transcr_maps_id
) as sub
where sub.themapid=refseq_transcr_maps_id and sub.theverid=refseq_transcr_ver_id;

insert into gnomad.max_variant_freq select loc_var_id, gnomad_version, max(alt_allele_freq) from gnomad.variant_freq group by loc_var_id, gnomad_version;
