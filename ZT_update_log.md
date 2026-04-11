# change log / 更新日志

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
