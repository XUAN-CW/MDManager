package edu.guet.markdowm_manager.utils;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.dircache.DirCache;
import org.eclipse.jgit.errors.RepositoryNotFoundException;
import org.eclipse.jgit.lib.Repository;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * @author XUAN
 * @date 2021/4/10 - 15:13
 * @references
 * @purpose 获取所有 .md 文件
 * @errors
 */
@Component
public class GetMarkdown {


    public List<File> getFileBySuffix(File dir){
        List<File> fileList = new LinkedList<>();
        recursiveTraversalFolder(dir,fileList);
        return fileList;
    }
    public List<File> getFileBySuffix(String path){
        return getFileBySuffix(new File(path));
    }


    private FileFilter MDFileFilter(){
        return file -> {
            if (file.isHidden()){
                return false;
            }
            if(!file.isDirectory()){
                if(!file.getName().endsWith(".md")){
                    return false;
                }
            }
            return true;
        };
    }

    private void recursiveTraversalFolder(File currentDir,List<File> fileList) {

        try {
            // 没有抛异常就说明这是个 Repository
            // 对于 git 仓库，我们有  git 仓库的处理方法
            // 处理完后直接 return
            List<File> markDownFiles = GetMarkdownFromRepository(currentDir);
            fileList.addAll(markDownFiles);
            return;
        } catch (RepositoryNotFoundException ignored) {
        } catch (IOException e) {
            e.printStackTrace();
        }

        File[] fileArr = currentDir.listFiles(MDFileFilter());
        if (null != fileArr && fileArr.length != 0) {
            for (File file : fileArr) {
                // 是文件夹，继续往下递归
                if (file.isDirectory()) {
                    recursiveTraversalFolder(file,fileList);
                }else {
                    fileList.add(file);
                }
            }
        }
    }

    private List<File> GetMarkdownFromRepository(File currentDir) throws IOException {
        Git git = Git.open(currentDir);
        Repository repository = git.getRepository();
        DirCache index = DirCache.read(repository);
        List<File> fileList = new LinkedList<>();
        for (int i = 0; i < index.getEntryCount(); i++) {
            File markdownFile = new File(repository.getWorkTree().getAbsolutePath()+File.separator+index.getEntry(i).getPathString());
            if(markdownFile.getName().endsWith(".md")){
                fileList.add(markdownFile);
            }
        }
        return  fileList;
    }

}
