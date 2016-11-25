# Hospitals-Residents Problem

a.k.a. College Admissions Problem

Implement the National Resident Matching Program (NMRA) algorithm (Gale-Shapley) for the hospitals-residents matching problem.

## Notes

* National Resident Matching Program
    * Website http://www.nrmp.org/match-process/match-algorithm/
    * Algorithm Example: http://www.nrmp.org/wp-content/uploads/2014/05/Run-A-Match.pdf
* The Gale-Shapley algorithm (also referred to as Deferred-Acceptance algorithm) for the stable marriage and the hospitals/residents problem is available as part of the matchingMarkets package
    * https://cran.r-project.org/web/packages/matchingMarkets/index.html
    * Analysis of Stable Matchings in R: https://cran.r-project.org/web/packages/matchingMarkets/vignettes/matching.pdf
    * hri: A constraint model (Prosser 2014) for the stable marriage and college admissions problem, a.k.a. hospital/residents problem.
        * In R: https://github.com/thiloklein/matchingMarkets
        * In Ada (data generated with Python): https://github.com/vishnuravi/stablematch
            * Docker for Ada: https://hub.docker.com/r/nacyot/ada-gnat/
        * Import and Export CSV in ASP.NET Core: https://damienbod.com/2016/06/17/import-export-csv-in-asp-net-core/
        * Import, Export ASP.NET Core localized data as CSV: https://damienbod.com/2016/07/15/import-export-asp-net-core-localized-data-as-csv/
* Building in Java
    * CSV Reader: https://commons.apache.org/proper/commons-csv/
    * Excel Document Reader: https://poi.apache.org/
        * POI-XSSF
* Build as Office Macro
    * Build your first Word add-in: https://dev.office.com/docs/add-ins/word/word-add-ins
        * Word add-in = manifest.xml + web app
    * Build your first Excel add-in: https://dev.office.com/docs/add-ins/excel/build-your-first-excel-add-in
        * Insert -> Store
        * https://dev.office.com/blogs/build2016release?utm_campaign=Build+2&utm_medium=bitly&utm_source=OneNote+Day+2
        * Add your manifest to a known directory:
            * mkdir ~/Library/Containers/com.microsoft.Excel/Data/Documents/wef
        * Debug tool: http://vorlonjs.com/
        * Vorlon extension to test OfficeJS api: https://blogs.msdn.microsoft.com/mim/2016/02/18/vorlonjs-plugin-for-debugging-office-addin/

