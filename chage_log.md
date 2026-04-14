# change log / 更新日志

---

## [12 - 04 - 2024]

### Modificaciones / 修改内容

**Lin**

- Reorganizar los archivos para estructurar el proyecto bajo el modelo MVC. / 调整文件结构以构建 MVC 架构。
- Añadir la carpeta `target/` al archivo `.gitignore.` / 在 `.gitignore` 中添加 `target/` 目录以忽略构建产物。

---

## [11 - 04 - 2024]

### Actualizaciones / 更新内容

**Nuevas Clases / 新增类**
|Estructura actual / 现结构 | Relacion / 关系 |
| :----------------------- | :-------------: |
|||
|**_clases nuevos_**|**11/04/2026**|
| Estudio | `independiente` |
| ├─ Estudio ← Fecha | `incluye` |
| ├─ Estudio ← Paciente | `incluye` |
| └─ Estudio ← Profesional | `incluye` |
| | |
|Analisis|`independiente`|
| Fecha | `independiente` |
| | |
|**_clases existentes_**|_previos_|
| Persona `abstracto` | `independiente` |
| ├─ Persona ← Paciente | `subclase` |
| └─ Persona ← Profesional | `subclase` |

### Modificaciones / 修改内容

**Lin**

- Completaron los `modificadores de acceso` que faltan en las clases: `Persona`, `Paciente`, `Profesional`. / 补齐了三个类的访问权限。
- Agregué la declaración `package ...` en la clase `Fecha` para incluirlo en el proyecto. / 补全`Fecha`类的package声明，加入至包结构。
- Cambio de nombre de `setter` & `getter` relacionado con `obraSocial`. / 方法名称微调。
- Clase: `Estudio` / 新增并修改了一系列细节
  - Nuevo instante `String[] analisis`
  - Nuevo método `setAnalisis(String... analisis)`
  - Modificar método `toString()` para mostrar las analisis
- Clase: `Proyecto_2026` / 新增常量
  - Nuevo constante `private static final Analisis TIPO`
- Método `toString()` para `Persona` `Paciente` `Profesional` / 新增了方法

---

## [06 - 04 - 2024]

### Actualizaciones / 更新内容

**Nuevas Clases / 新增类**
|Estructura actual / 现结构 | Relacion / 关系 |
| :------------------ | :-------------: |
| | |
|**_clases nuevos_**|**06/04/2026**|
| Persona `abstracto` | `independiente` |
| ├─ Persona ← Paciente | `subclase` |
| └─ Persona ← Profesional | `subclase` |
|**_clases existentes_**|_previos_|
|||

---
