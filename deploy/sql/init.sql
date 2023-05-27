CREATE
DATABASE SmarthomeDB;

CREATE TABLE users
(
    user_id       serial NOT NULL,
    user_name     text   NOT NULL,
    user_surname  text   NOT NULL,
    user_login    text   NOT NULL,
    user_password text   NOT NULL,
    PRIMARY KEY (user_id)
);

CREATE TABLE hubs
(
    hub_id     serial
        constraint hubs_pkey primary key,
    hub_owner  serial
        constraint hubs_hub_owner_users_user_id_foreign references users,
    hub_name   text not null,
    hub_uuid   text not null,
    hub_secret text not null
);

CREATE TABLE devices
(
    device_id        serial NOT NULL,
    device_name      text   NOT NULL,
    device_location  text   NOT NULL,
    device_serial    text   NOT NULL,
    local_ip_address text   NOT NULL,
    device_owner     serial NOT NULL,
    hub_id           serial NOT NULL,
    device_type      text   NOT NULL,
    json_properties  jsonb,
        PRIMARY KEY (device_id),
    CONSTRAINT devices_hub_id_hubs_hub_id_foreign FOREIGN KEY (hub_id) REFERENCES hubs (hub_id),
    CONSTRAINT devices_device_owner_users_user_id_foreign FOREIGN KEY (device_owner) REFERENCES users (user_id)
);

CREATE TABLE shared_devices
(
    share_id          serial NOT NULL,
    device_owner      serial NOT NULL,
    allowed_user      serial NOT NULL,
    shared_device     serial NOT NULL,
    sharing_date_from date DEFAULT NULL,
    sharing_date_to   date DEFAULT NULL,
    PRIMARY KEY (share_id),
    CONSTRAINT shared_devices_device_owner_user_user_id_foreign FOREIGN KEY (device_owner) REFERENCES users (user_id),
    CONSTRAINT shared_devices_allowed_user_user_user_id_foreign FOREIGN KEY (allowed_user) REFERENCES users (user_id),
    CONSTRAINT shared_devices_shared_device_device_device_id_foreign FOREIGN KEY (shared_device) REFERENCES devices (device_id)
);

INSERT INTO public.users (user_id, user_name, user_surname, user_login, user_password) VALUES (11, 'Egor', 'Fedorenko', 'efedoren', '$2a$12$mZVqoTCaubVF2uKlGTbNYuWVlxt00OzsbYfTD9b7CbMqVxeSbWrZ.');
INSERT INTO public.users (user_id, user_name, user_surname, user_login, user_password) VALUES (22, 'Vladlen', 'VLADLEN', 'vshershe', '$2a$12$TXPVDWpf3fcrDTz/ks3fMO.gG49cItcKwL4YnPOQtMGuPLokltPBa');

INSERT INTO public.hubs (hub_id, hub_owner, hub_name, hub_uuid, hub_secret) VALUES (3, 11, 'wed', 'dddd1', 'sdsfre');
INSERT INTO public.hubs (hub_id, hub_owner, hub_name, hub_uuid, hub_secret) VALUES (1, 11, 'Xiaomi', '8a76gd1q', '$2a$12$cbPsYeYdY/bYV1VRO/LLpOyiq4m5WxgB1l4Hmr0aWULnOFgsWFhPa');
INSERT INTO public.hubs (hub_id, hub_owner, hub_name, hub_uuid, hub_secret) VALUES (2, 22, 'dss', '8a76gd1', '$2a$12$cbPsYeYdY/bYV1VRO/LLpOyiq4m5WxgB1l4Hmr0aWULnOFgsWFhPa');

INSERT INTO public.devices (device_id, device_name, device_location, device_serial, local_ip_address, device_owner, hub_id, device_type, json_properties) VALUES (3, 'Bitches', 'Somewhere', 'sdesdd', '192.33.22.1', 22, 2, 'BULB', '{"values": {"b": "255", "g": "255", "r": "255", "isOn": "true", "brightness": "100"}}');
INSERT INTO public.devices (device_id, device_name, device_location, device_serial, local_ip_address, device_owner, hub_id, device_type, json_properties) VALUES (4, 'Vacuum cleaner', 'Flat 1', 'VCMCLNR', '192.168.0.199', 11, 1, 'VACUUM_CLEANER', '{"values": {"b": "255", "g": "255", "r": "255", "isOn": "true", "brightness": "100"}}');
INSERT INTO public.devices (device_id, device_name, device_location, device_serial, local_ip_address, device_owner, hub_id, device_type, json_properties) VALUES (5, 'Web camera', 'Guest room', 'CMR', '111.111.111.111', 11, 3, 'CAMERA', '{"values": {"b": "255", "g": "255", "r": "255", "isOn": "true", "brightness": "100"}}');
INSERT INTO public.devices (device_id, device_name, device_location, device_serial, local_ip_address, device_owner, hub_id, device_type, json_properties) VALUES (1, 'Lightning bulb 1', 'Kitchen', 'eWeLink_10016c3b68', '192.168.0.103', 11, 1, 'BULB', '{"values": {"b": "255", "g": "255", "r": "255", "isOn": "true", "brightness": "100"}}');
INSERT INTO public.devices (device_id, device_name, device_location, device_serial, local_ip_address, device_owner, hub_id, device_type, json_properties) VALUES (2, 'Lightning bulb 2', 'Guest Room', 'eWeLink_10016c3e33', '192.168.0.183', 11, 1, 'BULB', '{"values": {"b": "255", "g": "255", "r": "255", "isOn": "true", "brightness": "100"}}');

INSERT INTO public.shared_devices (share_id, device_owner, allowed_user, shared_device, sharing_date_from, sharing_date_to) VALUES (1, 22, 11, 3, '2023-05-01', '2023-05-15');