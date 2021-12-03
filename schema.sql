
    create table asociaciones (
        asociacion_id bigint not null auto_increment,
        nombre varchar(255),
        ubicacion_id bigint,
        primary key (asociacion_id)
    )

    create table caracteristicas_mascotas (
        respuesta_id bigint not null,
        pregunta_id bigint,
        mascota_id bigint,
        primary key (respuesta_id)
    )

    create table contactos (
        contacto_id bigint not null auto_increment,
        apellido varchar(255),
        mail varchar(255),
        nombre varchar(255),
        telefono varchar(255),
        vinculo varchar(255),
        duenio_id bigint,
        primary key (contacto_id)
    )

    create table duenios (
        duenio_id bigint not null auto_increment,
        apellido varchar(255),
        fecha_nacimiento date,
        nombre varchar(255),
        numero_identificacion varchar(255),
        tipo_identificacion varchar(255),
        usuario_id bigint,
        primary key (duenio_id)
    )

    create table mascotas (
        mascota_id bigint not null auto_increment,
        apodo varchar(255),
        descripcion_fisica varchar(255),
        edad_aproximada double precision,
        nombre varchar(255),
        sexo varchar(255),
        situacion_mascota varchar(255),
        tipo_mascota varchar(255),
        duenio_id bigint,
        primary key (mascota_id)
    )

    create table mascotas_fotos (
        mascota_id bigint not null,
        foto varchar(255) not null
    )

    create table opciones (
        opcion_id bigint not null auto_increment,
        descripcion varchar(255),
        pregunta_id bigint,
        primary key (opcion_id)
    )

    create table opciones_respuestas (
        respuesta_id bigint not null,
        opcion_id bigint not null
    )

    create table preguntas (
        tipo_input varchar(31) not null,
        pregunta_id bigint not null auto_increment,
        descripcion varchar(255),
        obligatoria bit,
        tipo varchar(255) not null,
        primary key (pregunta_id)
    )

    create table preguntas_adopcion (
        asociacion_id bigint not null,
        pregunta_id bigint not null
    )

    create table preguntas_objetivos (
        pregunta_id bigint not null,
        objetivo varchar(255) not null
    )

    create table publicaciones_adopcion (
        publicacion_adopcion_id bigint not null auto_increment,
        activa bit,
        asociacion_id bigint,
        duenio_id bigint,
        mascota_id bigint,
        primary key (publicacion_adopcion_id)
    )

    create table publicaciones_interes_adopcion (
        publicacion_interes_adopcion_id bigint not null auto_increment,
        activa bit,
        duenio_id bigint,
        primary key (publicacion_interes_adopcion_id)
    )

    create table publicaciones_rescates (
        publicacion_rescate_id bigint not null auto_increment,
        estado varchar(255),
        asociacion_id bigint,
        rescate_id bigint,
        primary key (publicacion_rescate_id)
    )

    create table rescates (
        tipo_rescate varchar(31) not null,
        rescate_id bigint not null auto_increment,
        rescate_descripcion varchar(255),
        fecha_nacimiento date,
        ubicacion_id bigint,
        mascota_id bigint,
        rescatista_id bigint,
        primary key (rescate_id)
    )

    create table rescatistas (
        rescatista_id bigint not null auto_increment,
        apellido varchar(255),
        fecha_nacimiento date,
        nombre varchar(255),
        numero_identificacion varchar(255),
        tipo_identificacion varchar(255),
        contacto_id bigint,
        ubicacion_id bigint,
        usuario_id bigint,
        primary key (rescatista_id)
    )

    create table respuestas_interes_adopcion (
        respuesta_id bigint not null,
        pregunta_id bigint,
        publicacion_interes_adopcion_id bigint,
        primary key (respuesta_id)
    )

    create table respuestas_publicaciones_adopciones (
        respuesta_id bigint not null,
        pregunta_id bigint,
        publicacion_adopcion_id bigint,
        primary key (respuesta_id)
    )

    create table ubicaciones (
        ubicacion_id bigint not null auto_increment,
        codigo_postal varchar(255),
        direccion varchar(255),
        latitud decimal(19,2),
        localidad varchar(255),
        longitud decimal(19,2),
        primary key (ubicacion_id)
    )

    create table usuarios (
        usuario_id bigint not null auto_increment,
        contrasenia varchar(255),
        nombre_usuario varchar(255),
        tipo_usuario varchar(255),
        primary key (usuario_id)
    )

    create table voluntarios (
        voluntario_id bigint not null auto_increment,
        asociacion_id bigint,
        usuario_id bigint,
        primary key (voluntario_id)
    )

    alter table mascotas_fotos 
        add constraint UK_8jrlj0152qvffs829aex3g1w3  unique (mascota_id, foto)

    alter table asociaciones 
        add constraint FK_p65rpqdtcf7acm61m5m6oxp4a 
        foreign key (ubicacion_id) 
        references ubicaciones (ubicacion_id)

    alter table caracteristicas_mascotas 
        add constraint FK_nf8eet6jp3ea02m1bxqdm7vb9 
        foreign key (mascota_id) 
        references mascotas (mascota_id)

    alter table caracteristicas_mascotas 
        add constraint FK_pbp4f0r2ff73qt0ilhc0a47fy 
        foreign key (pregunta_id) 
        references preguntas (pregunta_id)

    alter table contactos 
        add constraint FK_2gncfufnorrtg0gvtu6ubwj4g 
        foreign key (duenio_id) 
        references duenios (duenio_id)

    alter table duenios 
        add constraint FK_dhlk11eo6mx7uwiwyy1srreno 
        foreign key (usuario_id) 
        references usuarios (usuario_id)

    alter table mascotas 
        add constraint FK_rrryiaj66wmjgs25gvok8t9k8 
        foreign key (duenio_id) 
        references duenios (duenio_id)

    alter table mascotas_fotos 
        add constraint FK_co0w2wjvorccs34t2ofeh47yc 
        foreign key (mascota_id) 
        references mascotas (mascota_id)

    alter table opciones 
        add constraint FK_ikdmgghxqyhnv9qld4q15t83o 
        foreign key (pregunta_id) 
        references preguntas (pregunta_id)

    alter table opciones_respuestas 
        add constraint FK_tp5ww9yu5hfimrxaud86v80pc 
        foreign key (opcion_id) 
        references opciones (opcion_id)

    alter table preguntas_adopcion 
        add constraint FK_shq7n2lx8nidefl84ej3ywc6p 
        foreign key (pregunta_id) 
        references preguntas (pregunta_id)

    alter table preguntas_adopcion 
        add constraint FK_3evls270s819ciwic9u2fpljj 
        foreign key (asociacion_id) 
        references asociaciones (asociacion_id)

    alter table preguntas_objetivos 
        add constraint FK_mfka7vbcapyay9m5gfrcwu4k1 
        foreign key (pregunta_id) 
        references preguntas (pregunta_id)

    alter table publicaciones_adopcion 
        add constraint FK_l45jl1qumygcohgso1y5qfv0x 
        foreign key (asociacion_id) 
        references asociaciones (asociacion_id)

    alter table publicaciones_adopcion 
        add constraint FK_p8e98j0wps4yb8oude88bbigm 
        foreign key (duenio_id) 
        references duenios (duenio_id)

    alter table publicaciones_adopcion 
        add constraint FK_s1qfxtq9vjcnnqcnor4rils1i 
        foreign key (mascota_id) 
        references mascotas (mascota_id)

    alter table publicaciones_interes_adopcion 
        add constraint FK_ld7dgw2eum3bm1s6bj1tyf1c2 
        foreign key (duenio_id) 
        references duenios (duenio_id)

    alter table publicaciones_rescates 
        add constraint FK_97u5f617ytkeyvhmpl3hffx73 
        foreign key (asociacion_id) 
        references asociaciones (asociacion_id)

    alter table publicaciones_rescates 
        add constraint FK_1bqn337ot8yfjh2gqxk3w0but 
        foreign key (rescate_id) 
        references rescates (rescate_id)

    alter table rescates 
        add constraint FK_bct178526n9qif1nkoj6uff56 
        foreign key (ubicacion_id) 
        references ubicaciones (ubicacion_id)

    alter table rescates 
        add constraint FK_3kfg88718xkqf3tw8dubyd0db 
        foreign key (mascota_id) 
        references mascotas (mascota_id)

    alter table rescates 
        add constraint FK_8ysfq750ehns2h2v4sq77k4cn 
        foreign key (rescatista_id) 
        references rescatistas (rescatista_id)

    alter table rescatistas 
        add constraint FK_mvwfhdhn88u4w8wqylx5a9p9c 
        foreign key (contacto_id) 
        references contactos (contacto_id)

    alter table rescatistas 
        add constraint FK_c4ldm1lvg0wdbavsxjmeqlt0r 
        foreign key (ubicacion_id) 
        references ubicaciones (ubicacion_id)

    alter table rescatistas 
        add constraint FK_ml8b1aac2ic5wljdp8hf1d4gs 
        foreign key (usuario_id) 
        references usuarios (usuario_id)

    alter table respuestas_interes_adopcion 
        add constraint FK_fc74sq6akbsudyrrj2p8jp799 
        foreign key (publicacion_interes_adopcion_id) 
        references publicaciones_interes_adopcion (publicacion_interes_adopcion_id)

    alter table respuestas_interes_adopcion 
        add constraint FK_jgw9bttgttvn7ytpxu8gnv4x6 
        foreign key (pregunta_id) 
        references preguntas (pregunta_id)

    alter table respuestas_publicaciones_adopciones 
        add constraint FK_a8dqraxlnlni1p1384mrhumkv 
        foreign key (publicacion_adopcion_id) 
        references publicaciones_adopcion (publicacion_adopcion_id)

    alter table respuestas_publicaciones_adopciones 
        add constraint FK_h3kaa24puqpmm2y824ae6nrkf 
        foreign key (pregunta_id) 
        references preguntas (pregunta_id)

    alter table voluntarios 
        add constraint FK_bocttaw72ejdslrvrhq73mda0 
        foreign key (asociacion_id) 
        references asociaciones (asociacion_id)

    alter table voluntarios 
        add constraint FK_k5rl3o3rgyyjvn475nt4r6v3q 
        foreign key (usuario_id) 
        references usuarios (usuario_id)

    create table hibernate_sequences (
         sequence_name varchar(255),
         sequence_next_hi_value integer 
    )
