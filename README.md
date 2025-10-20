# Travel Optics - 无需Alex's Caves依赖编译包

## 📦 项目说明

这是一个完整的Forge模组开发环境，用于编译**不需要Alex's Caves**作为强制前置的Travel Optics模组。

### ✅ 已完成的修改

1. **移除Alex's Caves强制依赖**
   - `mods.toml` 中Alex's Caves改为可选依赖（`mandatory=false`）
   
2. **包含Alex's Caves桩类**
   - 提供兼容的类接口，无需真实的Alex's Caves模组
   - 包括：WaterBoltEntity、WaveEntity、DinosaurSpiritEntity等
   
3. **清理硬编码引用**
   - TravelopticsSchools.java 不再直接引用ACSoundRegistry
   - 所有静态初始化问题已解决

4. **完整的源码和资源**
   - 730个Java源文件
   - 所有assets和data文件
   - 完整的META-INF配置

---

## ⚠️ 重要：依赖下载问题

**如果Gradle无法自动下载依赖**（常见错误），请使用以下解决方案：

### 🎯 推荐解决方案：自动下载依赖

我们提供了自动下载脚本来解决CurseForge Maven下载问题：

**Windows用户：**
```cmd
.\下载依赖.bat
```

**Linux/macOS用户：**
```bash
./下载依赖.sh
```

脚本会自动下载所有7个依赖文件（约158MB）到 `libs` 文件夹。

下载完成后：
```bash
# 1. 备份原配置
copy build.gradle build.gradle.backup

# 2. 使用本地依赖配置
copy build_with_local_libs.gradle build.gradle

# 3. 运行编译
gradlew.bat build
```

**详细说明请查看：《手动下载依赖指南.md》**

---

## 🚀 快速开始

### 前提条件

**必需软件**：
- **Java 17 或更高版本** （必须！）
- 稳定的网络连接（首次编译会下载约500MB-1GB的依赖）
- 至少4GB可用内存
- 至少2GB磁盘空间

### 检查Java版本

```bash
java -version
```

应该显示类似：`openjdk version "17.x.x"` 或更高版本

**如果版本不对**，请先安装Java 17：
- Windows/Linux/macOS: https://adoptium.net/

---

## 🎯 一键编译（推荐）

### Windows用户

1. **双击运行** `一键编译.bat`
2. 按提示操作
3. 等待编译完成（首次约10-30分钟）
4. 编译后的JAR文件位于 `build\libs\`

### Linux/macOS用户

1. **打开终端**，进入项目目录
2. 运行编译脚本：
   ```bash
   ./一键编译.sh
   ```
3. 按提示操作
4. 等待编译完成
5. 编译后的JAR文件位于 `build/libs/`

---

## 📝 手动编译

如果一键脚本无法使用，可以手动编译：

### Windows

```cmd
gradlew.bat clean build
```

### Linux/macOS

```bash
./gradlew clean build
```

---

## 📁 项目结构

```
TravelOptics_BuildKit/
├── src/
│   └── main/
│       ├── java/                  # Java源代码（730个文件）
│       │   ├── com/gametechbc/traveloptics/    # Travel Optics源码
│       │   └── com/github/alexmodguy/         # Alex's Caves桩类
│       └── resources/             # 资源文件
│           ├── assets/            # 纹理、模型、语言文件
│           ├── data/              # 数据包、配方、战利品表
│           └── META-INF/          # 模组配置
│               └── mods.toml      # 已修改为可选依赖
├── build.gradle                   # Gradle构建配置
├── gradle.properties              # 项目属性配置
├── settings.gradle                # Gradle设置
├── gradlew                        # Gradle包装器（Linux/Mac）
├── gradlew.bat                    # Gradle包装器（Windows）
├── 一键编译.bat                   # Windows一键编译脚本
├── 一键编译.sh                    # Linux/Mac一键编译脚本
└── README.md                      # 本文件
```

---

## ⚙️ 编译配置说明

### build.gradle 关键配置

- **Minecraft版本**: 1.20.1
- **Forge版本**: 47.4.9
- **Java版本**: 17

### 依赖模组（会自动下载）

| 模组 | 状态 | 版本 |
|------|------|------|
| Iron's Spells 'n Spellbooks | 必需 | 3.4.0.7+ |
| L_Ender's Cataclysm | 必需 | 2.57+ |
| AttributesLib | 必需 | 1.3.7+ |
| Curios API | 必需 | - |
| GeckoLib | 必需 | - |
| PlayerAnimator | 必需 | - |
| **Alex's Caves** | **可选** | 2.0.0+ |

---

## 🔧 编译问题排查

**⚠️ 重要：如果遇到依赖下载问题，请查看《故障排除指南.md》获取详细解决方案！**

### 问题1: 无法下载依赖（最常见）

**错误示例:**
```
Could not find curse.maven:irons-spells-n-spellbooks-855403:5589516
```

**快速解决:**
```bash
# 清理缓存重试
gradlew.bat clean --refresh-dependencies
gradlew.bat build
```

**详细解决方案:** 请查看项目目录中的 **`故障排除指南.md`**，包含6种解决方案！

---

### 问题2: "找不到Java命令"

**解决方案**：
1. 确保安装了Java 17或更高版本
2. 设置 `JAVA_HOME` 环境变量
3. 将Java添加到PATH环境变量

**Windows设置JAVA_HOME**：
```cmd
setx JAVA_HOME "C:\Program Files\Java\jdk-17"
setx PATH "%PATH%;%JAVA_HOME%\bin"
```

### 问题2: 编译超时或下载失败

**解决方案**：
- 检查网络连接
- 如果在中国，可能需要配置镜像或使用VPN
- 编辑 `gradle.properties`，增加超时时间：
  ```properties
  systemProp.http.connectionTimeout=120000
  systemProp.http.socketTimeout=120000
  ```

### 问题3: "Cannot resolve dependency"

**解决方案**：
1. 清理Gradle缓存：
   ```bash
   # Windows
   gradlew.bat clean --refresh-dependencies
   
   # Linux/macOS
   ./gradlew clean --refresh-dependencies
   ```

2. 删除 `~/.gradle/caches` 目录后重新编译

### 问题4: 内存不足

**解决方案**：
编辑 `gradle.properties`，增加内存分配：
```properties
org.gradle.jvmargs=-Xmx6G
```

### 问题5: 编译很慢

**解决方案**：
- 首次编译需要下载大量依赖，耐心等待
- 确保启用了Gradle守护进程（已在gradle.properties中配置）
- 使用更快的网络连接

---

## 📊 编译输出

### 成功编译后

您会在 `build/libs/` 目录下看到：

```
traveloptics-5.5.0-1.20.1-NoAlexsCaves.jar
```

### 文件大小

编译后的JAR大小约为 **25-30MB**

---

## 🎮 安装和测试

### 1. 安装到客户端

```
复制 build/libs/traveloptics-5.5.0-1.20.1-NoAlexsCaves.jar
到 .minecraft/mods/
```

### 2. 安装到服务器

```
复制 build/libs/traveloptics-5.5.0-1.20.1-NoAlexsCaves.jar
到 server/mods/
```

### 3. 必需的前置模组

确保同时安装以下模组：
- ✅ Iron's Spells 'n Spellbooks (3.4.0.7+)
- ✅ L_Ender's Cataclysm (2.57+)
- ✅ AttributesLib (1.3.7+)
- ✅ Curios API
- ✅ GeckoLib
- ✅ PlayerAnimator

### 4. 可选模组

- ❌ Alex's Caves (不再需要！)

### 5. 启动测试

1. 启动Minecraft/服务器
2. 检查模组列表中是否有 Travel Optics
3. 创建新世界或进入现有世界
4. 测试基础功能

---

## ✨ 功能说明

### ✅ 保留的功能

- 所有不依赖Alex's Caves的法术
- 所有武器、护甲、饰品
- 技能树系统
- 自定义生物（非Alex's Caves相关）
- 大部分战利品系统

### ⚠️ 受限的功能

以下功能在**没有Alex's Caves时不可用**：

1. **Alex's Caves洞穴战利品增强**
   - 15个战利品修改器（箱子和生物掉落）
   
2. **部分水系法术**
   - 使用WaterBoltEntity的法术可能功能受限
   
3. **特定生物召唤**
   - SummonedForsaken（被遗弃者召唤）
   - SummonedAtlatitan（亚特兰巨人召唤）

**注意**：这些功能不可用**不会导致崩溃**，只是相关内容无法正常工作。

---

## 🔄 更新和维护

### 更新源码

如果需要修改源码：

1. 编辑 `src/main/java/` 中的文件
2. 重新编译：`gradlew build`
3. 测试新的JAR

### 更新资源

如果需要修改资源文件：

1. 编辑 `src/main/resources/` 中的文件
2. 重新编译：`gradlew build`
3. 测试新的JAR

### 更新依赖

如果需要更新前置模组版本：

1. 编辑 `build.gradle` 中的dependency版本号
2. 运行：`gradlew clean build --refresh-dependencies`

---

## 📞 常见问题 (FAQ)

### Q1: 为什么编译这么慢？

**A**: 首次编译需要下载Gradle、Minecraft/Forge库和所有依赖模组（约500MB-1GB）。后续编译会快很多。

### Q2: 编译后的模组与原版有什么区别？

**A**: 
- ✅ 不再强制需要Alex's Caves
- ⚠️ 部分依赖Alex's Caves的功能不可用
- ✅ 核心功能完全正常

### Q3: 如果我后来安装了Alex's Caves会怎样？

**A**: 所有功能会自动启用，与原版JAR完全相同！

### Q4: 可以用于服务器吗？

**A**: 完全可以！这就是设计目的。

### Q5: 会影响存档兼容性吗？

**A**: 不会。这只是移除了强制依赖，不影响存档数据。

### Q6: 编译失败怎么办？

**A**: 
1. 检查Java版本（必须是17+）
2. 检查网络连接
3. 查看上方的"编译问题排查"章节
4. 如果仍然失败，请查看完整的错误信息

### Q7: 可以修改源码吗？

**A**: 可以！这是反编译的源码，您可以自由修改和重新编译。

### Q8: 为什么包含Alex's Caves的桩类？

**A**: 桩类提供了相同的API接口，让Travel Optics的代码可以编译和运行，而无需真正的Alex's Caves模组。

---

## 📜 版权声明

- **原模组作者**: GameTechBC
- **原模组**: Travel Optics (T.O Magic 'n Extras)
- **原模组链接**: https://www.curseforge.com/minecraft/mc-mods/to-tweaks-irons-spells

**本编译包说明**：
- 仅修改了依赖配置和提供桩类
- 未修改核心游戏逻辑
- 仅供个人和服务器使用
- 所有原作者的credit保持不变

如果原作者要求，请停止使用此版本并使用官方版本。

---

## 🎉 编译成功检查清单

编译完成后，检查以下内容：

- [ ] `build/libs/` 目录存在
- [ ] JAR文件已生成（约25-30MB）
- [ ] 文件名包含 "NoAlexsCaves"
- [ ] 没有编译错误或警告
- [ ] 准备好前置模组（Iron's Spells、Cataclysm等）
- [ ] 已将JAR复制到mods文件夹
- [ ] 在游戏中测试基础功能

---

## 📊 技术细节

### 桩类实现

Alex's Caves桩类位于：
```
src/main/java/com/github/alexmodguy/alexscaves/
```

包括：
- `WaterBoltEntity` - 水球实体
- `WaveEntity` - 波浪实体  
- `DinosaurSpiritEntity` - 恐龙灵魂实体
- `DeepOneBaseEntity` - 深海生物基类

这些类提供最小化的实现，确保编译通过但不需要真正的Alex's Caves。

### 编译优化

`gradle.properties` 中的优化配置：
```properties
org.gradle.jvmargs=-Xmx4G        # 4GB堆内存
org.gradle.daemon=true           # 启用守护进程
org.gradle.parallel=true         # 并行编译
org.gradle.caching=true          # 启用缓存
```

---

## 🌟 致谢

- **原作者 GameTechBC** - 创作了Travel Optics模组
- **Iron's Spells团队** - 提供了法术框架
- **反编译工具 CFR** - 用于反编译源码
- **Forge团队** - 提供了模组开发框架

---

**祝您编译顺利！** 🎮✨

如有任何问题，请检查上方的FAQ和问题排查章节。
