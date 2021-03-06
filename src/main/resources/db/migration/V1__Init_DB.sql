CREATE TABLE public.cart (
                           id bigint NOT NULL,
                           user_id bigint NOT NULL
);

CREATE SEQUENCE public.cart_id_seq
  START WITH 1
  INCREMENT BY 1
  NO MINVALUE
  NO MAXVALUE
  CACHE 1;

ALTER SEQUENCE public.cart_id_seq OWNED BY public.cart.id;

CREATE TABLE public.cart_item (
                                id bigint NOT NULL,
                                user_id bigint NOT NULL,
                                product_id bigint NOT NULL,
                                quantity bigint NOT NULL
);

CREATE SEQUENCE public.cart_item_id_seq
  START WITH 1
  INCREMENT BY 1
  NO MINVALUE
  NO MAXVALUE
  CACHE 1;


ALTER SEQUENCE public.cart_item_id_seq OWNED BY public.cart_item.id;


CREATE TABLE public.category (
                               id bigint NOT NULL,
                               title character varying(50) NOT NULL,
                               parent_id bigint
);



CREATE SEQUENCE public.category_id_seq
  START WITH 1
  INCREMENT BY 1
  NO MINVALUE
  NO MAXVALUE
  CACHE 1;


ALTER SEQUENCE public.category_id_seq OWNED BY public.category.id;

CREATE TABLE public.order_item (
                                 id bigint NOT NULL,
                                 order_id bigint NOT NULL,
                                 product_id bigint NOT NULL,
                                 quantity bigint NOT NULL,
                                 price integer NOT NULL,
                                 total_price integer NOT NULL
);


CREATE SEQUENCE public.order_item_id_seq
  START WITH 1
  INCREMENT BY 1
  NO MINVALUE
  NO MAXVALUE
  CACHE 1;

ALTER SEQUENCE public.order_item_id_seq OWNED BY public.order_item.id;


CREATE TABLE public.order_table (
                                  id bigint NOT NULL,
                                  user_id bigint NOT NULL,
                                  time_created timestamp without time zone NOT NULL,
                                  payment_type character varying(50) NOT NULL,
                                  address character varying(255) NOT NULL,
                                  finalprice integer NOT NULL,
                                  orderstatus character varying(50) NOT NULL,
                                  quantity integer,
                                  payment_status character varying(50) NOT NULL,
                                  phone character varying(15) NOT NULL
);


CREATE SEQUENCE public.order_table_id_seq
  START WITH 1
  INCREMENT BY 1
  NO MINVALUE
  NO MAXVALUE
  CACHE 1;



ALTER SEQUENCE public.order_table_id_seq OWNED BY public.order_table.id;


CREATE TABLE public.products (
                               description character varying(2048) NOT NULL,
                               price integer NOT NULL,
                               details_id bigint,
                               stock integer NOT NULL,
                               photo character varying(2048),
                               name character varying(255) NOT NULL,
                               category_id bigint NOT NULL,
                               active boolean NOT NULL,
                               id bigint NOT NULL
);




CREATE SEQUENCE public.products_id_seq
  START WITH 1
  INCREMENT BY 1
  NO MINVALUE
  NO MAXVALUE
  CACHE 1;


ALTER SEQUENCE public.products_id_seq OWNED BY public.products.id;


CREATE TABLE public.roles (
                            id bigint NOT NULL,
                            name character varying(20) NOT NULL
);


CREATE SEQUENCE public.roles_id_seq
  START WITH 1
  INCREMENT BY 1
  NO MINVALUE
  NO MAXVALUE
  CACHE 1;

ALTER SEQUENCE public.roles_id_seq OWNED BY public.roles.id;

CREATE TABLE public.user_contacts (
                                    id bigint NOT NULL,
                                    user_id bigint NOT NULL,
                                    phone character varying(50),
                                    address character varying(255)
);




CREATE SEQUENCE public.user_contacts_id_seq
  START WITH 1
  INCREMENT BY 1
  NO MINVALUE
  NO MAXVALUE
  CACHE 1;

ALTER SEQUENCE public.user_contacts_id_seq OWNED BY public.user_contacts.id;


CREATE TABLE public.user_roles (
                                 user_id bigint NOT NULL,
                                 role_id bigint NOT NULL
);


CREATE TABLE public.usr (
                          id bigint NOT NULL,
                          first_name character varying(50) NOT NULL,
                          last_name character varying(50) NOT NULL,
                          password character varying(255) NOT NULL,
                          email character varying(50) NOT NULL,
                          active boolean,
                          code character varying(50)
);



CREATE SEQUENCE public.usr_id_seq
  START WITH 1
  INCREMENT BY 1
  NO MINVALUE
  NO MAXVALUE
  CACHE 1;


ALTER SEQUENCE public.usr_id_seq OWNED BY public.usr.id;

ALTER TABLE ONLY public.cart ALTER COLUMN id SET DEFAULT nextval('public.cart_id_seq'::regclass);


ALTER TABLE ONLY public.cart_item ALTER COLUMN id SET DEFAULT nextval('public.cart_item_id_seq'::regclass);

ALTER TABLE ONLY public.category ALTER COLUMN id SET DEFAULT nextval('public.category_id_seq'::regclass);


ALTER TABLE ONLY public.order_item ALTER COLUMN id SET DEFAULT nextval('public.order_item_id_seq'::regclass);


ALTER TABLE ONLY public.order_table ALTER COLUMN id SET DEFAULT nextval('public.order_table_id_seq'::regclass);


ALTER TABLE ONLY public.products ALTER COLUMN id SET DEFAULT nextval('public.products_id_seq'::regclass);


ALTER TABLE ONLY public.roles ALTER COLUMN id SET DEFAULT nextval('public.roles_id_seq'::regclass);


ALTER TABLE ONLY public.user_contacts ALTER COLUMN id SET DEFAULT nextval('public.user_contacts_id_seq'::regclass);


ALTER TABLE ONLY public.usr ALTER COLUMN id SET DEFAULT nextval('public.usr_id_seq'::regclass);

ALTER TABLE ONLY public.cart_item
  ADD CONSTRAINT cart_item_pkey PRIMARY KEY (id);


ALTER TABLE ONLY public.cart
  ADD CONSTRAINT cart_pkey PRIMARY KEY (id);


ALTER TABLE ONLY public.category
  ADD CONSTRAINT category_pkey PRIMARY KEY (id);


ALTER TABLE ONLY public.category
  ADD CONSTRAINT category_title_key UNIQUE (title);


ALTER TABLE ONLY public.order_item
  ADD CONSTRAINT order_item_pkey PRIMARY KEY (id);


ALTER TABLE ONLY public.order_table
  ADD CONSTRAINT order_table_pkey PRIMARY KEY (id);



ALTER TABLE ONLY public.roles
  ADD CONSTRAINT pk_roles PRIMARY KEY (id);



ALTER TABLE ONLY public.user_roles
  ADD CONSTRAINT pk_user_role PRIMARY KEY (user_id, role_id);



ALTER TABLE ONLY public.usr
  ADD CONSTRAINT pk_usr PRIMARY KEY (id);


ALTER TABLE ONLY public.products
  ADD CONSTRAINT products_pk PRIMARY KEY (id);


ALTER TABLE ONLY public.user_contacts
  ADD CONSTRAINT user_contacts_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.cart_item
  ADD CONSTRAINT cart_item_usr__fk FOREIGN KEY (user_id) REFERENCES public.usr(id) ON UPDATE CASCADE ON DELETE RESTRICT;


ALTER TABLE ONLY public.products
  ADD CONSTRAINT fk_category_category FOREIGN KEY (category_id) REFERENCES public.category(id) ON DELETE RESTRICT;



ALTER TABLE ONLY public.order_item
  ADD CONSTRAINT fk_order_id_order FOREIGN KEY (order_id) REFERENCES public.order_table(id) ON UPDATE CASCADE ON DELETE RESTRICT;



ALTER TABLE ONLY public.order_item
  ADD CONSTRAINT fk_product_id_products FOREIGN KEY (product_id) REFERENCES public.products(id) ON UPDATE CASCADE ON DELETE RESTRICT;



ALTER TABLE ONLY public.cart_item
  ADD CONSTRAINT fk_product_id_products FOREIGN KEY (product_id) REFERENCES public.products(id) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE ONLY public.user_roles
  ADD CONSTRAINT fk_user_id_usr FOREIGN KEY (user_id) REFERENCES public.usr(id) ON DELETE RESTRICT;



ALTER TABLE ONLY public.order_table
  ADD CONSTRAINT fk_user_id_usr FOREIGN KEY (user_id) REFERENCES public.usr(id) ON UPDATE CASCADE ON DELETE RESTRICT;


ALTER TABLE ONLY public.cart
  ADD CONSTRAINT fk_user_id_usr FOREIGN KEY (user_id) REFERENCES public.usr(id) ON UPDATE CASCADE ON DELETE RESTRICT;


ALTER TABLE ONLY public.user_contacts
  ADD CONSTRAINT fk_user_id_usr FOREIGN KEY (user_id) REFERENCES public.usr(id) ON DELETE RESTRICT;


ALTER TABLE ONLY public.user_roles
  ADD CONSTRAINT fk_user_role_roles FOREIGN KEY (role_id) REFERENCES public.roles(id) ON DELETE RESTRICT;


