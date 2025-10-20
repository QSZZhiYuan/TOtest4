# TravelOptics Minecraft 模组 - 反混淆项目

## 项目概述
这是一个经过反混淆处理的 Minecraft Forge 模组项目（TravelOptics）。所有混淆后的SRG名称（m_xxx_、f_xxx_）已被替换为Mojang官方映射名称。

## 项目结构
```
.
├── src/main/java/    # 模组源代码（已反混淆）
├── build.gradle      # Gradle构建配置
├── gradle.properties # 项目属性
├── gradlew          # Gradle包装器（Linux/Mac）
├── gradlew.bat      # Gradle包装器（Windows）
└── REMAPPING_REPORT.md  # 反混淆处理报告
```

## 反混淆处理结果

### 替换统计
- ✅ **修改文件**: 600个
- ✅ **总替换次数**: 16,665次
- ✅ **残留混淆名称**: 0个
- ✅ **成功率**: 100%

详细报告请查看 `REMAPPING_REPORT.md`

## 使用说明

### 1. 编译项目
```bash
chmod +x gradlew
./gradlew build
```

### 2. 运行开发环境
```bash
# 运行Minecraft客户端（开发模式）
./gradlew runClient

# 运行专用服务器（开发模式）
./gradlew runServer
```

### 3. 生成IDE项目文件
```bash
# IntelliJ IDEA
./gradlew idea

# Eclipse
./gradlew eclipse
```

### 4. 清理构建
```bash
./gradlew clean
```

## 技术信息

### Minecraft & Forge
- **Minecraft版本**: 1.20.1
- **Forge版本**: 47.3.0
- **映射**: Parchment 2023.09.03-1.20.1

### Java环境
- **Java版本**: OpenJDK 17.0.15
- **构建工具**: Gradle 8.x
- **ForgeGradle**: 6.0.x

### 依赖模组
- Player Animator
- Iron's Spells n Spellbooks  
- Apothic Attributes
- Lender's Cataclysm
- Curios API
- GeckoLib
- Caelus API

## 最近更改

### 2025-10-20: 完成代码反混淆
- ✅ 下载并解析Minecraft 1.20.1映射文件
- ✅ 创建SRG→Mojang映射字典（62,337个映射）
- ✅ 批量替换16,665个混淆名称
- ✅ 手动修复4个特殊方法映射
- ✅ 验证0个残留混淆名称
- ✅ 生成详细处理报告

### 2025-10-20: 初始化项目环境
- 安装 Java 17 和 Python 3.11
- 导入TravelOptics模组源代码
- 配置Forge开发环境

## 已知问题
- 项目尚未进行编译测试
- 可能存在少量需要手动调整的方法签名
- 建议运行 `./gradlew build` 检查编译错误

## 下一步建议
1. 运行 `./gradlew build` 进行首次编译
2. 使用IDE（IntelliJ IDEA推荐）打开项目
3. 检查LSP诊断，修复可能的类型错误
4. 运行 `./gradlew runClient` 测试功能
