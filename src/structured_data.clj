(ns structured-data)

(defn do-a-thing [x]
  (let [xx (+ x x)]
    (Math/pow xx xx)))

(defn spiff [v]
  (+ (get v 0) (get v 2)))

(defn cutify [v]
  (conj v "<3"))

(defn spiff-destructuring [v]
  (let [[x, _, y] v]
    (+ x y)))

(defn point [x y]
  [x y])

(defn rectangle [bottom-left top-right]
  [bottom-left top-right])

(defn width [rectangle]
  (let [[[x1, _], [x2, _]] rectangle]
    (- x2 x1)))

(defn height [rectangle]
  (let [[[_, y1], [_, y2]] rectangle]
    (- y2 y1)))

(defn square? [rectangle]
  (== (height rectangle) (width rectangle)))

(defn area [rectangle]
  (* (height rectangle) (width rectangle)))

(defn contains-point? [rectangle point]
  (let [[[x1, y1], [x2, y2]] rectangle
        [x3, y3] point]
    (and (<= x1 x3 x2)
         (<= y1 y3 y2))))

(defn contains-rectangle? [outer inner]
  (let [[p1, p2] inner]
    (and (contains-point? outer p1)
         (contains-point? outer p2))))

(defn title-length [book]
  (count (:title book)))

(defn author-count [book]
  (count (:authors book)))

(defn multiple-authors? [book]
  (> (author-count book) 1))

(defn add-author [book new-author]
  (let [authors (:authors book)
        new-authors (conj authors new-author)]
    (assoc book :authors new-authors)))

(defn alive? [author]
  (not (contains? author :death-year)))

(defn element-lengths [collection]
  (map (fn [element] (count element))
       collection))

(defn second-elements [collection]
  (let [secondify (fn [el] (get el 1))]
    (map secondify collection)))

(defn titles [books]
  (map :title books))

(defn monotonic? [a-seq]
  (or (apply <= a-seq)
      (apply >= a-seq)))

(defn stars [n]
  (apply str (repeat n "*")))

(defn toggle [a-set elem]
  (if (contains? a-set elem)
      (disj a-set elem)
      (conj a-set elem)))

(defn contains-duplicates? [a-seq]
  (not (= (count (set a-seq)) (count a-seq))))

(defn old-book->new-book [book]
  (let [new-authors (set (:authors book))]
    (assoc book :authors new-authors)))

(defn has-author? [book author]
  (contains? (:authors book) author))

(defn authors [books]
  (apply clojure.set/union (map :authors books)))

(defn all-author-names [books]
  (set (map :name (authors books))))

(defn author->string [author]
  (let [name (:name author)
        year (if (contains? author :birth-year)
                 (str " (" (:birth-year author) " - " (:death-year author) ")"))]
    (str name year)))

(defn authors->string [authors]
  (let [author-strings (map author->string authors)
        interposed (interpose ", " author-strings)]
    (apply str interposed)))

(defn book->string [book]
  (let [authors-string (authors->string (:authors book))
        title (:title book)]
    (str title ", written by " authors-string)))

(defn books->string [books]
  (let [books-count (count books)
        count-string (cond
                        (= books-count 0) "No books"
                        (= books-count 1) "1 book. "
                        :else (str books-count " books. "))
        books-strings (map book->string books)
        books-interposed (interpose ". " books-strings)
        books-string (apply str books-interposed)]
    (str count-string books-string ".")))

(defn books-by-author [author books]
  (filter (fn [book] (has-author? book author)) books))

(defn author-by-name [name authors]
  (first (filter (fn [author] (= name (:name author))) authors)))

(defn living-authors [authors]
  (filter alive? authors))

(defn has-a-living-author? [book]
  (let [authors (:authors book)
        living-authors (filter alive? authors)]
    (not (empty? living-authors))))

(defn books-by-living-authors [books]
  (filter has-a-living-author? books))

; %________%
