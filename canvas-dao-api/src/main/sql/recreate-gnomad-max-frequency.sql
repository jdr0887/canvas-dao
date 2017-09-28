
drop index gnomad.max_variant_freq_loc_var_id_idx;
delete from gnomad.max_variant_freq;
insert into gnomad.max_variant_freq select loc_var_id, gnomad_version, max(alt_allele_freq) from gnomad.variant_freq group by loc_var_id, gnomad_version;
create index gnomad_max_variant_freq_loc_var_id_idx on gnomad.max_variant_freq (loc_var_id);
