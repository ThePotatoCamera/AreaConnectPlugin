# El plugin de conexión con el servidor de Discord de Area223

Debido a que Area223 oficialmente ha cerrado sus puertas, he publicado este proyecto bajo la licencia MIT para su libre uso e inspiración por cualquiera.

Este repo se archivó en 2022, por lo que **NO admite ninguna PR o issue**. Seguramente falte trabajo por hacer, pero esa es la parte de la gracia de que se haya publicado. ;)

La idea de este plugin es conectar con un bot de Discord para que se puedan vincular las cuentas de Discord y Minecraft. Bastante útil si quieres realizar alguna dinámica de recibir recompensas en Minecraft por hablar en Discord o viceversa. El cielo es el límite en cuanto a lo que se puede hacer con algo así. Había un sistema base para economía entre servidores. **La versión de API a la que apunta es la 1.18**.

## ¿Qué trabajo está ya hecho?

Si mal no recuerdo, está ya la lógica para realizar la conexión con Discord desde Minecraft. Devuelve un código al usuario tras ejecutar el comando que debe introducir en un bot de Discord.

## Comandos

Comando | Alias | Parámetros | Permiso | Descripción
--------|-------|------------|---------|-------------
areaconnect | area-connect \| a223 | `areaconnect.reload` | reload \| link \| adddinero | Gestionar el plugin de AreaConnect

## Permisos

Permiso | Nodo | Predeterminado| Descripción | Hijos
--------|------|---------------|-------------|-------
Global | `areaconnect.*` | `op` | Acceso adminstrativo del plugin | `areaconnect.reload` `areaconnect.link` `areaconnect.addmoney` `areaconnect.removemoney` `areaconnect.setmoney`
Recargar | `areaconnect.reload` | `op` | Permite recargar el plugin
Link | `areaconnect.link` | `true` | Permite enlazar una cuenta de Minecraft a una cuenta de Discord
Unlink | `areaconnect.unlink` | `true` | Permite desenlazar una cuenta de Minecraft de una cuenta de Discord
Addmoney | `areaconnect.addmoney` | `op` | Permite añadir dinero a un usuario
Removemoney | `areaconnect.removemoney` | `op` | Permite quitar dinero a un usuario
Setmoney | `areaconnect.setmoney` | `op` | Permite cambiar el dinero de un usuario

**Para mas info acerca de los comandos y los permisos, revisa el archivo [plugin.yml](https://github.com/ThePotatoCamera/AreaConnectPlugin/blob/master/src/main/resources/plugin.yml).

## Configuraciones a la base de datos
Se usa **MariaDB** como base de datos del plugin. No se agregó soporte para SQLite, por lo que queda en tus manos si quieres agregarlo. Puedes modificar las opciones de la base de datos desde el archivo [config.yml](https://github.com/ThePotatoCamera/AreaConnectPlugin/blob/master/src/main/resources/config.yml).

Si haces cosas chulas con el plugin, [házmelo saber en Twitter](https://twitter.com/thepotatocamera).
