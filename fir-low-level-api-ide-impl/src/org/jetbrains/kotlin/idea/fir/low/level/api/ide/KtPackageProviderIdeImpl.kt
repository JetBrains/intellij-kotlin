package org.jetbrains.kotlin.idea.fir.low.level.api.ide

import com.intellij.openapi.project.Project
import com.intellij.psi.search.GlobalSearchScope
import org.jetbrains.kotlin.idea.fir.low.level.api.KtPackageProvider
import org.jetbrains.kotlin.idea.fir.low.level.api.KtPackageProviderFactory
import org.jetbrains.kotlin.idea.stubindex.PackageIndexUtil
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.name.Name
import org.jetbrains.kotlin.platform.TargetPlatform
import org.jetbrains.kotlin.idea.util.module

internal class KtPackageProviderIdeImpl(
    private val project: Project,
    private val searchScope: GlobalSearchScope,
) : KtPackageProvider() {

    override fun isPackageExists(packageFqName: FqName): Boolean =
        PackageIndexUtil.packageExists(packageFqName, searchScope, project)

    override fun getKotlinSubPackageFqNames(packageFqName: FqName): Set<Name> {
        return PackageIndexUtil
            .getSubPackageFqNames(packageFqName, searchScope, project) { true }
            .mapTo(mutableSetOf()) { it.shortName() }
    }
}

class KtPackageProviderFactoryIdeImpl(private val project: Project) : KtPackageProviderFactory() {
    override fun createPackageProvider(searchScope: GlobalSearchScope): KtPackageProvider {
        return KtPackageProviderIdeImpl(project, searchScope)
    }
}